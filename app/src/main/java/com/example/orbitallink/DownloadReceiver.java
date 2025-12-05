package com.example.orbitallink;

import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class DownloadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            String channelId = "canal_descargas_orbital";
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Descargas de Misiones",
                    NotificationManager.IMPORTANCE_HIGH
            );
            manager.createNotificationChannel(channel);

            Intent i = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

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