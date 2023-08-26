package com.example.sohelvpn.view.activities;

import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;

import com.example.sohelvpn.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class MyVpnService extends VpnService {
    private Thread vpnThread;
    private ParcelFileDescriptor vpnInterface;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (vpnThread != null) {
            return START_STICKY;
        }

        vpnThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    establishVpnConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    stopSelf();
                }
            }
        });

        vpnThread.start();
        return START_STICKY;
    }

    private void establishVpnConnection() throws Exception {
        // Configure the VPN interface settings
        VpnService.Builder builder = new VpnService.Builder();
        builder.setSession(getString(R.string.app_name));
        builder.addAddress("10.0.0.1", 32); // Set the IP address of the VPN interface
        builder.addRoute("0.0.0.0", 0); // Route all traffic through the VPN
        vpnInterface = builder.establish();

        if (vpnInterface == null) {
            throw new Exception("Failed to establish VPN interface");
        }

        DatagramChannel tunnel = DatagramChannel.open();
        tunnel.connect(new InetSocketAddress("VPN_SERVER_ADDRESS", 12345)); // Replace with your VPN server details

        protect(tunnel.socket());

        FileInputStream in = new FileInputStream(vpnInterface.getFileDescriptor());
        FileOutputStream out = new FileOutputStream(vpnInterface.getFileDescriptor());

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            int bytesRead = in.read(buffer.array());
            if (bytesRead > 0) {
                buffer.limit(bytesRead);
                tunnel.write(buffer);
                buffer.clear();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (vpnThread != null) {
            vpnThread.interrupt();
        }
        if (vpnInterface != null) {
            try {
                vpnInterface.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}

