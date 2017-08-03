import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity; 
import android.os.Bundle; import android.view.View;
import android.widget.TextView; 
public class MainActivity extends AppCompatActivity {
@Override
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
}
private static final int j=1234;
public void buGetsms(View view) 
{
if ((int) Build.VERSION.SDK_INT>=23)
{
if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){ if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS))
{ 
requestPermissions(new String[]{Manifest.permission.READ_SMS},j);
}
return;
}
}
loadmessga();
}
public void onRequestPermissionsResult (int requestCode , String[] permissions, int[]grantResults)
{ 
switch (requestCode)
{
case j: if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
{
loadmessga(); 
}
else{} break; 
default: super.onRequestPermissionsResult(requestCode,permissions,grantResults);
}
}
void loadmessga()
{
try{
String sms = ""; Uri usms = Uri.parse("content://sms/inbox");
Cursor cur = getContentResolver().query(usms,null,null,null,null);
cur.moveToPosition(0);
while (cur.moveToNext()){ sms +="from : "+cur.getString(cur.getColumnIndex("address"))+ " :: "+cur.getString(cur.getColumnIndex("body"))+"\n"; } TextView txt = (TextView) findViewById(R.id.textView); txt.setText(sms);
}
catch (Exception ex){
}
}
}
