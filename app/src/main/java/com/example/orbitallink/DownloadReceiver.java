package com.example.orbitallink;

import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class DownloadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        long completedId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
        DownloadManager managerD = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = managerD.getUriForDownloadedFile(completedId);

        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            String channelId = "canal_descargas_orbital";
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Descargas de Misiones",
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(channel);

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(uri,"image/*");
            i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    i,
                    PendingIntent.FLAG_IMMUTABLE
            );

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setContentTitle("Parche de misión guardado")
                    .setContentText("Pulsa para ver el archivo.")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            manager.notify(100, builder.build());

            Toast.makeText(context, "Descarga completada", Toast.LENGTH_SHORT).show();
        }
    }
}