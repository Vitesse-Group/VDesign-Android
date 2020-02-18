package com.vitesse.group.vghomedecor.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogHelper {

    Context context;
    AlertDialog alertDialog=null;
    AlertDialogListener callBack;
    Activity current_activity;

    public AlertDialogHelper(Context context)
    {
        this.context = context;
        this.current_activity = (Activity) context;
        callBack = (AlertDialogListener) context;
    }

    /**
     * Displays the AlertDialog with 3 Action buttons
     *
     * you can set cancelable property
     *
     * @param title
     * @param message
     * @param positive
     * @param negative
     * @param neutral
     * @param from
     * @param isCancelable
     */
    public void showAlertDialog(String title,String message,String positive,String negative,String neutral,final int from,boolean isCancelable)
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(current_activity);

        if(!TextUtils.isEmpty(title))
            alertDialogBuilder.setTitle(title);
        if(!TextUtils.isEmpty(message))
            alertDialogBuilder.setMessage(message);

        if(!TextUtils.isEmpty(positive)) {
            alertDialogBuilder.setPositiveButton(positive,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            callBack.onPositiveClick(from);
                            alertDialog.dismiss();
                        }
                    });
        }
        if(!TextUtils.isEmpty(neutral)) {
            alertDialogBuilder.setNeutralButton(neutral,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            callBack.onNeutralClick(from);
                            alertDialog.dismiss();
                        }
                    });
        }
        if(!TextUtils.isEmpty(negative))
        {
            alertDialogBuilder.setNegativeButton(negative,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            callBack.onNegativeClick(from);
                            alertDialog.dismiss();
                        }
                    });
        }

        alertDialogBuilder.setCancelable(isCancelable);


        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        if(TextUtils.isEmpty(negative))
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
    }

    /**
     * Displays the AlertDialog with positive action button only
     *
     * you can set cancelable property
     *
     * @param title
     * @param message
     * @param positive
     * @param from
     * @param isCancelable
     */
    public void showAlertDialog(String title,String message,String positive,final int from,boolean isCancelable)
    {
        showAlertDialog(title,message,positive,"","",from,isCancelable);
    }

    /**
     * Displays the AlertDialog with positive action button only
     *
     * cancelable property is false (Default)
     *
     * @param title
     * @param message
     * @param positive
     * @param from
     */
    public void showAlertDialog(String title,String message,String positive,final int from)
    {
        showAlertDialog(title,message,positive,"","",from,false);
    }


    /**
     *
     * Displays the AlertDialog with positive & negative buttons
     *
     * you can set cancelable property
     *
     * @param title
     * @param message
     * @param positive
     * @param negative
     * @param from
     * @param isCancelable
     */

    public void showAlertDialog(String title,String message,String positive,String negative,final int from,boolean isCancelable)
    {
        showAlertDialog(title,message,positive,negative,"",from,isCancelable);
    }

    /**
     *
     * Displays the AlertDialog with positive & negative buttons
     *
     * cancelable property is false (Default)
     *
     * @param title
     * @param message
     * @param positive
     * @param negative
     * @param from
     */
    public void showAlertDialog(String title,String message,String positive,String negative,final int from)
    {
        showAlertDialog(title,message,positive,negative,"",from,false);
    }

    /**
     * Displays the AlertDialog with 3 Action buttons
     *
     * cancelable property is false (Default)
     *
     * @param title
     * @param message
     * @param positive
     * @param negative
     * @param neutral
     * @param from
     */
    public void showAlertDialog(String title,String message,String positive,String negative,String neutral,final int from)
    {
        showAlertDialog(title,message,positive,negative,neutral,from,false);
    }


    public interface AlertDialogListener
    {
        public void onPositiveClick(int from);
        public void onNegativeClick(int from);
        public void onNeutralClick(int from);
    }
}
