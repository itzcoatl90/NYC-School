package com.itz.corp.error.handler;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.itz.corp.R;

public class ErrorHandler {

    private ErrorHandler() {}

    public static void showErrorDialog(Context context) {
        showErrorDialog(context, context.getString(R.string.no_message));
    }

    public static void showErrorDialog(Context context, Throwable t) {
        showErrorDialog(context, t.getMessage());
    }

    public static void showErrorDialog(Context context, String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String message = context.getString(R.string.expecting_answers) + JUMP_LINE + error;
        builder.setTitle(R.string.what_a_terrible_failure)
                .setMessage(message)
                .setPositiveButton(R.string.will_try_again, (dialog, id) -> { /* Do nothing for now, maybe crashlytics? */ })
                .create()
                .show();
    }

    private static final String JUMP_LINE = "\n";
}
