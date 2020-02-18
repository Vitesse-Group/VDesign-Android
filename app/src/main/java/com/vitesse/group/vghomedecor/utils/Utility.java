package com.vitesse.group.vghomedecor.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;


import com.vitesse.group.vghomedecor.R;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Utility {
    private static ProgressDialog progressDialog;
    public static int TAB_LIVING_AREA_INDEX = 0;
    public static int TAB_BEDROOM_AREA_INDEX = 1;
    public static int TAB_KITCHEN_AREA_INDEX = 2;
    public static String LIVING_IMAGE_TAG = "living_image";
    public static String KITCHEN_IMAGE_TAG = "kitchen_image";
    public static String BEDROOM_IMAGE_TAG = "bedroom_image";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+-\\.]+(\\+-\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final  String VGHOMEDECOR_DIRECTORY = "/vg_home_decor" ;
    private static final  String VGHOMEDECOR_FILENAME = "vg_decor" ;
   public static File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            + VGHOMEDECOR_DIRECTORY);
    private static final String TAG = Utility.class.getSimpleName();
    public static final InputFilter EMOJI_FILTER = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            /*for (int index = start; index < end; index++) {

                int type = Character.getType(source.charAt(index));

                if (type == Character.SURROGATE) {
                    return "";
                }
            }
            return null;*/
            if (source.equals("") || source.equals("€") || source.equals("£")) { // for backspace
                return source;
            }
            if (source.toString().matches("[\\x00-\\x7F]+")) {
                return source;
            }
            return "";
        }
    };
    private static ProgressDialog mProgressDialog;
    private static AlertDialog mDialog;
    private static Dialog outsideUKDialog;
    /**
     * Checks whether the entered email is a valid one or not using
     * pattern matcher.
     *
     * @param email Email id entered by the user
     * @return Returns true if the email id matches the pattern, otherwise returns false.
     */
    public static boolean isValidEmail(final String email) {
        String temp = email;
        if (!TextUtils.isEmpty(temp)) {
            temp = temp.replaceFirst("\\s+$", "");
            final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            final Matcher matcher = pattern.matcher(temp);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * Checks whether the password contains at least eight characters or not.
     *
     * @param passwordText password text entered by the user
     * @return true if the password contains at least eight characters else returns false.
     */
    public static boolean hasAtLeastEightChar(String passwordText) {
        return passwordText.length() >= 8;
    }

    /**
     * Checks whether the password has upper case character or not.
     *
     * @param passwordText password text entered by the user
     * @return true if the password contains an upper case character else returns false.
     */
    public static boolean hasUpperCase(String passwordText) {
        int stringLength = passwordText.length();
        while (stringLength != 0) {
            final char singleChar = passwordText.charAt(stringLength - 1);
            stringLength--;
            if (Character.isUpperCase(singleChar)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the password has lower case character or not.
     *
     * @param passwordText password text entered by the user
     * @return true if the password contains an lower case character else returns false.
     */
    public static boolean hasLowerCase(String passwordText) {
        int stringLength = passwordText.length();
        while (stringLength != 0) {
            final char singleChar = passwordText.charAt(stringLength - 1);
            stringLength--;
            if (Character.isLowerCase(singleChar)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the password contains a number or not.
     *
     * @param passwordText password text entered by the user
     * @return true if the password contains an lower case character else returns false.
     */
    public static boolean hasNumber(String passwordText) {
        int stringLength = passwordText.length();
        while (stringLength != 0) {
            final char singleChar = passwordText.charAt(stringLength - 1);
            stringLength--;
            if (Character.isDigit(singleChar)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the password contains a special character or not.
     *
     * @param s password text entered by the user
     * @return true if the password contains a special character, else returns false.
     */
    public static boolean hasSpecialChar(String s) {
        int stringLength = s.length();
        while (stringLength != 0) {
            final char singleChar = s.charAt(stringLength - 1);
            stringLength--;
            if (!Character.isLetterOrDigit(singleChar) && !s.matches("[a-zA-Z.? ]*")) {
                return true;
            }

        }
        return false;
    }

    /**
     * Checks whether the password contains a special character or not.
     *
     * @param passwordText password text entered by the user
     * @return true if the password contains a special character, else returns false.
     */
    public static boolean hasSpecialCharWithSpaceAllowed(String passwordText) {
        int stringLength = passwordText.length();
        while (stringLength != 0) {
            final char singleChar = passwordText.charAt(stringLength - 1);
            stringLength--;
            if (!Character.isLetterOrDigit(singleChar) && !passwordText.matches("[ \\s a-zA-Z0-9.? ]*")) {
                return true;
            }

        }
        return false;
    }

    public static boolean isAsciiPrintable(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!isAsciiPrintable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAsciiPrintable(char ch) {
        return ch >= 32 && ch <= 126;
    }




    public static boolean isValidPasswordRegex(String password) {
        Pattern re = Pattern.compile("^(?=.{8,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).*");
        Matcher m = re.matcher(password);
        return m.find();
    }

    /**
     * Takes password string as an input and checks the validity of it
     * If it is valid, then returns true, otherwise returns false.
     *
     * @param password password string entered by user
     * @return
     */
    public static boolean isValidPassword(String password) {
        if ((hasNumber(password) || hasSpecialChar(password)) && hasAtLeastEightChar(password))
            if (hasLowerCase(password)) if (hasUpperCase(password)) return true;
        return false;
    }

    /**
     * Returns the device id to the application.
     *
     * @param context current application context.
     * @return
     */
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /**
     * Returns the type of card from the card number entered by the user
     *
     * @param credCardNumber Card number entered by the user
     * @return Returns the type of card
     */
   /* public static String getCardType(final String credCardNumber) {
        String creditCard = credCardNumber.trim();

        if (creditCard.startsWith("4")) {
            return AppConstants.VISA;
        }
        if (creditCard.startsWith("5")) {
            int prefix = Integer.parseInt(creditCard.substring(0, 2));
            if (prefix >= 51 && prefix <= 59) {
                return AppConstants.MASTERCARD;
            }
        }
        if (creditCard.startsWith("34") || creditCard
                .startsWith("37")) {
            return AppConstants.AMEX;
        }
        return AppConstants.NONE;
    }*/

    /**
     * Returns network connectivity information
     *
     * @param context The application context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        } else {
            final ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }




    public static void showProgress(Context context, String message) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        int style;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            style = android.R.style.Theme_Material_Light_Dialog;
        } else {
            //noinspection deprecation
            style = ProgressDialog.THEME_HOLO_LIGHT;
        }

        progressDialog = new ProgressDialog(context, style);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    /**
     * This method is generic, used by the application has to display messages using AlertDialog.
     * The alert dialog will have only "OK" button. On click of "OK" the alert dialog will be
     * removed.
     *
     * @param message alert message to be displayed
     */
    public static void showAlertMessage(final Activity activity, final String message) {
        showAlertMessage(activity, message);
    }
    /**
     * This method is generic, used by the application has to display messages using AlertDialog.
     * The alert dialog will have only "OK" button. On click of "OK" the alert dialog will be
     * removed.
     *
     * @param message alert message to be displayed
     */
    public static void showAlertMessage(final Activity activity, final String message, DialogInterface.OnClickListener listener) {
        showAlertMessage(activity, message,listener );
    }

    /**
     * This method will show the alert message to the user if it uses the application
     * outside of the UK region.
     * If user is in Fuelfinder screen then on click ok OK button, it should stay in same screen.
     * Otherwise user should navigate back to dashboard screen.
     * If the app is accessed outside UK, then user can do
     * @param context
     * @param message
     */



    public static void showAlertMessage(final Context context, final String message, final boolean dismiss) {
        try {
            if (mDialog != null && mDialog.isShowing())
                mDialog.dismiss();

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context,
                    R.style.alertDialogTheme);

            alertDialog.setMessage(message);
            alertDialog.setCancelable(dismiss);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            mDialog = alertDialog.show();
        } catch (Exception e) {
           // Log.e(AppConstants.EXCPTN_TAG, e.getMessage(), e);
        }
    }




    /**
     * This method is generic, used by the application has to display messages using AlertDialog.
     * The alert dialog will have only "OK" button. On click of "OK" the alert dialog will be
     * removed.
     *
     * @param message alert message to be displayed
     */
    public static void showAlertMessage(final Activity activity, final String message, String title, DialogInterface.OnClickListener clickListener) {
        if (activity != null && !activity.isFinishing()) {
            if (mDialog != null && mDialog.isShowing()) {
                try {
                    mDialog.dismiss();
                } catch (Exception e) {
                  //  Log.e(AppConstants.EXCPTN_TAG, e.getMessage(), e);
                }
            }

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity,
                    R.style.alertDialogTheme);
            alertDialog.setMessage(message).setCancelable(false);
            if (!TextUtils.isEmpty(title)) {
                alertDialog.setTitle(title);
            }
            alertDialog.setPositiveButton("Ok", clickListener);
            alertDialog.setNegativeButton("Cancel",clickListener);
            mDialog = alertDialog.show();
        } else {
            Log.e(TAG, "Activity object null");
        }
    }





    /**
     * Takes a number string and the format(means after decimal point how many digits we should show)
     * as an input and returns a formatted number accordingly.
     *
     * @param number decimal number as a string
     * @param format decimal number format
     * @return Returns formatted decimal number
     */
    public static String formatDecimal(String number, String format) {
        String formattedValue = "";
        if (!TextUtils.isEmpty(number)) {
            try {
                Double dblVar = Double.parseDouble(number);
                DecimalFormat df = new DecimalFormat(format);
                System.out.println("Value: " + df.format(dblVar));
                formattedValue = df.format(dblVar);
            } catch (Exception pe) {
               // Log.e(AppConstants.EXCPTN_TAG, pe.getMessage(), pe);
                return formattedValue;
            }
        }
        return formattedValue;
    }


    public static void showAlertMessage(Context context, String message) {

        try {
            if (mDialog != null && mDialog.isShowing())
                mDialog.dismiss();

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context,
                    R.style.alertDialogTheme);
            alertDialog.setMessage(message).setCancelable(false);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            mDialog = alertDialog.show();
        } catch (Exception e) {
           // Log.e(AppConstants.EXCPTN_TAG, e.getMessage(), e);
        }
    }

    public static void showErrorView(AppCompatTextView mErrorTextView, String mErrorMsg) {
        mErrorTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setText(mErrorMsg);
    }



    /**
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(Activity activity) {
        View focusedView = activity.getCurrentFocus();
        if(focusedView != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public static void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static char[] generateOTP(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[length];

        for(int i = 0; i< length ; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return otp;
    }
}


