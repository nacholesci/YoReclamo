package utn.frsf.com.yoreclamo.Control;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

import utn.frsf.com.yoreclamo.Control.ApiRest.ReclamoApiRest;
import utn.frsf.com.yoreclamo.Entidad.Reclamo;
import utn.frsf.com.yoreclamo.R;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getBundleExtra("bundle");
        Log.i("Estado: ",(String) b.getString("Estado"));
        if(b!=null){
            if(((String) b.getString("Estado")).equals(context.getResources().getString(R.string.Reclamo_en_solucion))){
                // Vuelvo a ejecutar el servicio para cambiar de estado No Resuelto a En Solucion
                Intent intent_Notificacion = new Intent(/*AltaReclamo.this, MyReceiver.class*/"ResolverReclamo");
                Bundle bb = new Bundle();
                bb.putDouble("LatLng-Lat",b.getDouble("LatLng-Lat"));
                bb.putDouble("LatLng-Lng",b.getDouble("LatLng-Lng"));
                bb.putString("Estado", context.getResources().getString(R.string.Reclamo_resuelto));
                intent_Notificacion.putExtra("bundle",bb);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent_Notificacion,0);
                AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

                Random r = new Random();
                int tiempo = r.nextInt(15 - 7 + 1) + 7; // Entre 7 y 15 segundos demora en solucionar
                am.set(AlarmManager.RTC,System.currentTimeMillis() + tiempo * 1000,pendingIntent);

                // Se recibe una notificación que está en proceso de solucionar el reclamo emitido
                PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                        new Intent(), 0);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                                .setContentTitle("Estado del reclamo")
                                .setContentText("Recibimos su reclamo, está en proceso de solucionar el problema");
                mBuilder.setContentIntent(contentIntent);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND);
                mBuilder.setAutoCancel(true);
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());

                //Se actualiza el estado del reclamo
                ReclamoApiRest gestion = new ReclamoApiRest();
                Reclamo reclamo = gestion.buscarLatLng(new LatLng(b.getDouble("LatLng-Lat"),b.getDouble("LatLng-Lng")));
                reclamo.setEstado(context.getResources().getString(R.string.Reclamo_en_solucion));
                gestion.actualizar(reclamo);

            }
            else{
                // Se recibe una notificación que se solucionó el reclamo emitido
                PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                        new Intent(), 0);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                                .setContentTitle("Estado del reclamo")
                                .setContentText("Su reclamo está resuelto");
                mBuilder.setContentIntent(contentIntent);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND);
                mBuilder.setAutoCancel(true);
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(1, mBuilder.build());

                //Se actualiza el estado del reclamo
                ReclamoApiRest gestion = new ReclamoApiRest();
                Reclamo reclamo = gestion.buscarLatLng(new LatLng(b.getDouble("LatLng-Lat"),b.getDouble("LatLng-Lng")));
                reclamo.setEstado(context.getResources().getString(R.string.Reclamo_resuelto));
                gestion.actualizar(reclamo);
            }

        }
    }
}
