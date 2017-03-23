package com.example.peter.newsadmin.common.vew;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.peter.newsadmin.R;


/**
 * Created by Byron on 2017-01-10.
 * <p>
 * Dialog辅助类
 */

public class DialogUtil {

//    //单按钮信息无点击事件提示框
//    public static void showInfoDialog(Context context, String info) {
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        dialog.setContentView(R.layout.public_dialog_bad_network);
//        TextView msg = (TextView) dialog.getWindow().findViewById(R.id.dialog_msg);
//        Button sure = (Button) dialog.getWindow().findViewById(R.id.dialog_sure);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_dialog_shape);
//        msg.setText(info);
//        dialog.show();
//        sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//    }
//
//    //单按信息有点击事件提示框
//    public static void showInfoCheckDialog(Context context, String info, final int type, final DialogInfoClickListener listener) {
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        dialog.setContentView(R.layout.public_dialog_bad_network);
//        TextView msg = (TextView) dialog.getWindow().findViewById(R.id.dialog_msg);
//        Button sure = (Button) dialog.getWindow().findViewById(R.id.dialog_sure);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_dialog_shape);
//        msg.setText(info);
//        dialog.show();
//        sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                listener.clickYes(type);
//            }
//        });
//    }
//
//    //选择dialog
//    public static void showCheckDialog(Context context, String info, String checkInfo, String cancelInfo, final int type, final DialogTwoButtonClickListener clickListener) {
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        dialog.setContentView(R.layout.public_dialog_remove);
//        TextView msg = (TextView) dialog.getWindow().findViewById(R.id.dialog_msg);
//        Button sure = (Button) dialog.getWindow().findViewById(R.id.remove_sure);
//        Button cancel = (Button) dialog.getWindow().findViewById(R.id.remove_cancel);
//        msg.setText(info);
//        sure.setText(checkInfo);
//        cancel.setText(cancelInfo);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_dialog_shape);
//
//        sure.setText(checkInfo);
//        cancel.setText(cancelInfo);
//        dialog.show();
//        sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                clickListener.clickYes(type);
//
//            }
//        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                clickListener.clickNo(type);
//            }
//        });
//    }
//
//    //单按钮有标题多内容dialog
//    public static void showCheckMsgDialog(Context context, String titleMsg, String info, String checkInfo, final int type, final DialogOneButtonClickListener clickListener) {
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        dialog.setContentView(R.layout.public_dialog_one_check_msg);
//        TextView title = (TextView) dialog.getWindow().findViewById(R.id.dialog_title);
//        TextView msg = (TextView) dialog.getWindow().findViewById(R.id.dialog_msg);
//        Button sure = (Button) dialog.getWindow().findViewById(R.id.dialog_sure);
//        title.setText(titleMsg);
//        msg.setText(info);
//        sure.setText(checkInfo);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_dialog_shape);
//        dialog.show();
//        sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                clickListener.clickYes(type);
//
//            }
//        });
//    }
//
//    //版本更新dialog
//    public static void updateVersion(final Context context, final int type, final DialogUpdateClickListener listener) {
//
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        dialog.setContentView(R.layout.public_version_update_dialog);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_dialog_shape);
//        dialog.show();
//        Button sureButton = (Button) dialog.getWindow().findViewById(R.id.version_update_now);
//        Button cancelButton = (Button) dialog.getWindow().findViewById(R.id.version_update_cancel);
//        sureButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.clickYes(type);
//                dialog.dismiss();
//            }
//        });
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//    }
//
//    //邀请界面dialog
//    public static void invitationDialog(Context context, int type, final DialogInvitationListener listener) {
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        dialog.setContentView(R.layout.public_dialog_appointment_success_exclusive);
//        dialog.getWindow().setBackgroundDrawableResource(R.drawable.public_dialog_shape);
//        TextView title = (TextView) dialog.getWindow().findViewById(R.id.appointment_success);
//        if (type == DialogInvitationListener.FROM_PERSONAL_PAGE) {
//            title.setVisibility(View.GONE);
//        }
//        Button invitationWX = (Button) dialog.getWindow().findViewById(R.id.invitation_by_wx);
//        Button invitationMsg = (Button) dialog.getWindow().findViewById(R.id.invitation_by_message);
//        Button copyMsg = (Button) dialog.getWindow().findViewById(R.id.copy_msg);
//        Button cancel = (Button) dialog.findViewById(R.id.cancel_invitation);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        invitationMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.invitation_Message();
//                dialog.dismiss();
//            }
//        });
//        copyMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.invitation_CopyMsgToClipBoard();
//                dialog.dismiss();
//            }
//        });
//        invitationWX.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                listener.invitation_WX();
//            }
//        });
//        dialog.show();
//    }


}
