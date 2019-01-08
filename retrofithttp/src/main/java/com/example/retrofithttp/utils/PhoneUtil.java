package com.example.retrofithttp.utils;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.core.app.ActivityCompat;


/**
 * 手机工具类(拨号,发短信,发邮箱)
 *
 * @author Administrator
 */
public class PhoneUtil {
    /**
     * 在MainActivity里面获取，自动把值塞进去了
     */
    public static boolean isPad;

    /**
     * 判断是否有SIM卡,或SIM卡是否有用
     *
     * @param context
     * @return
     */
    public static boolean hasSIM(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int absent = manager.getSimState();
        //absent==1代表SIM卡不存在或不可用
        return absent != TelephonyManager.SIM_STATE_ABSENT;
    }

    public static void call(Context context, String phoneNumber) {
        if (null != context && !TextUtils.isEmpty(phoneNumber)) {

            //判断是否有sim卡
            if (hasSIM(context)) {
                Uri uri = Uri.parse("tel:" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},
                            100);
                    return;
                }
                context.startActivity(intent);
            } else {

                Toast.makeText(context, "请检查SIM卡是否插好", Toast.LENGTH_LONG).show();
            }
        } else {

            Toast.makeText(context, "电话号码为空", Toast.LENGTH_LONG).show();
        }
    }

    public static void sms(Context context, String phoneNumber) {
        if (null != context && !TextUtils.isEmpty(phoneNumber)) {
            Uri uri = Uri.parse("smsto:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            context.startActivity(intent);
        }
    }

    /**
     * 单发短信，绝对顶用
     *
     * @param phoneNum 电话号码
     * @param content  短信内容
     */
    public static void sendMessage(String phoneNum, String content) {
        SmsManager smsManager = SmsManager.getDefault();

        if (content.length() > 70) // 如果短信内容过长则分段发送
        {
            ArrayList<String> contents = smsManager.divideMessage(content);// 分割短信
            for (String msg : contents) {
                smsManager.sendTextMessage(phoneNum, null, msg, null, null);
            }
        } else// 一次性发送
        {
            smsManager.sendTextMessage(phoneNum, null, content, null, null);
        }

    }

    /**
     * 群发，后台群发，绝对顶用
     *
     * @param phones  电话号码集合
     * @param content 短信内容
     */
    public static void sendMessage(List<String> phones, String content, Context context) {
        SmsManager smsManager = SmsManager.getDefault();

        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        for (String phone : phones) {

            if (content.length() > 70) // 如果短信内容过长则分段发送
            {
                ArrayList<String> contents = smsManager.divideMessage(content);// 分割短信
                for (String msg : contents) {
                    smsManager.sendTextMessage(phone, null, msg, sentIntent, null);
                }
            } else// 一次性发送
            {
                smsManager.sendTextMessage(phone, null, content, sentIntent, null);
            }
        }
    }

    /**
     * 单发短信
     *
     * @param phoneNum
     * @param content
     * @param context
     */
    public static void sendMessage(String phoneNum, String content, Context context) {
        String uriString = "smsto:";
        uriString += phoneNum;
        Uri smsToUri = Uri.parse(uriString);
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", content);

        context.startActivity(mIntent);
    }

    public static boolean sendOneMsg(Context context, String phoneno, String msgstr) {
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
                new Intent("SENT_SMS_ACTION"), 0);

        // DeliverPI为了获得对方接受到之后返回的报告的
        //接收报告：就是发送方的短信发送到对方手机上之后，对方手机会返回给运营商一个信号，告知运营商收到短信，运营商再把这个信号发给发送方，发送方得到这个信号之后，
        Intent deliverIntent = new Intent("DELIVERED_SMS_ACTION");
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0,
                deliverIntent, 0);
        smsManager.sendTextMessage(phoneno, null, msgstr, sentPI, deliverPI);
        return true;

    }


    /**
     * 群发短信，貌似不顶用
     *
     * @param context
     * @param phones
     */
    public static void sendMessageToManyContact(Context context, String[] phones, String content) {
        String uriString = "smsto:";

        for (int i = 0; i < phones.length; i++) {
            String phone = phones[i];

            if (i < phones.length - 1) {
                uriString += phone + ",";
            } else {
                uriString += phone;
            }

        }
        // 这里得到的uri类似于 smsto:1350000001, 1350000002, 1350000003...
        Uri smsToUri = Uri.parse(uriString);
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", content);
        context.startActivity(mIntent);
    }

    public static String removeSpacePhoneNum(String numString) {
        if (TextUtils.isEmpty(numString)) {
            return numString;
        }
        return numString.replaceAll(" ", "");
    }

    public static String removeCrossLinePhoneNum(String numString) {
        if (TextUtils.isEmpty(numString)) {
            return numString;
        }
        return numString.replaceAll("-", "");
    }

    public static void sendEmail(String[] emails, String title, String content, Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");

        intent.putExtra(Intent.EXTRA_EMAIL, emails);// 邮箱列表
        intent.putExtra(Intent.EXTRA_SUBJECT, title);// 标题
        intent.putExtra(Intent.EXTRA_TEXT, content);// 内容
        context.startActivity(Intent.createChooser(intent, "send...."));
    }

    public static void getMobileConfig(Context context) {
        TelephonyManager telephoneManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // telephoneManager.get
    }

    /**
     * 获取设备ID 唯一标识
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String deviceId = null;

        String tmDeviceId = null;
        String tmSerialNumber = null;
        String androidId = null;

        TelephonyManager telephoneManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        tmDeviceId = telephoneManager.getDeviceId();
        // tmSerialNumber = telephoneManager.getSimSerialNumber();
        androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

        if (tmDeviceId == null) {
            tmDeviceId = "empty";
        }
        UUID uuid = new UUID(androidId.hashCode(), (long) tmDeviceId.hashCode() >> 32);
        deviceId = uuid.toString();

        return deviceId;
    }

//	/**
//	 * 获取手机所有的应用
//	 * @param activity
//	 * @return
//	 */
//	public static List<AppInfo> getAppList(Activity activity){
//		List<AppInfo> appList = new ArrayList<AppInfo>();
//		List<PackageInfo> packageList = activity.getPackageManager().getInstalledPackages(0);
//
//		//过滤应用名称
//		String apps = "灸法应用|产品计算器";
//		//因为12306有很多版本，所以要模糊匹配
//		String app12306 = "12306";
//		String app365 = "365日历";
//
//		for(PackageInfo packageInfo : packageList){
//			AppInfo tmpInfo =new AppInfo();
//			tmpInfo.appName = packageInfo.applicationInfo.loadLabel(activity.getPackageManager()).toString();
//
//			if(apps.contains(tmpInfo.appName) || tmpInfo.appName.contains(app12306)|| tmpInfo.appName.contains(app365)){
//				tmpInfo.packageName = packageInfo.packageName;
//				tmpInfo.versionName = packageInfo.versionName;
//				tmpInfo.versionCode = packageInfo.versionCode;
//				if(null != (packageInfo.activities) && packageInfo.activities.length > 0){
//					ActivityInfo info = packageInfo.activities[0];
//					tmpInfo.firstActivityInfo = info;
//				}
//				tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(activity.getPackageManager());
//				appList.add(tmpInfo);
//			}
//		}
//		return appList;
//	}

    /**
     * 打开浏览器
     *
     * @param url
     * @param context
     */
    public static void openBrowser(String url, Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    /**
     * 检查当前网络是否可用
     *
     * @return
     */

    public static int getNetworkType(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return 0;
        } else {
            boolean wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
            boolean internet = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
            if (wifi | internet) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
