package seu.com.androidlearn.test.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/21.
 */

public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
    public static class Builder {
        private CustomDialog mDialog;
        private Context mContext;
        private String mTitle;
        private String mContent;
        private boolean cancel;
        private String mPositiveText;
        private View.OnClickListener mPositiveListener;
        private View.OnClickListener mNegativeListener;
        private String mNegativeText;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder cancel(boolean allowCancel) {
            this.cancel = allowCancel;
            return this;
        }

        public Builder setPositiveButton(String text, View.OnClickListener listener) {
            this.mPositiveText = text;
            this.mPositiveListener = listener;
            return this;
        }

        public Builder setNegativeButton(String text, View.OnClickListener listener) {
            this.mNegativeText = text;
            this.mNegativeListener = listener;
            return this;
        }
        public CustomDialog build() {
            if (mDialog == null) {
                mDialog = new CustomDialog(mContext, R.style.custom_dialog);
            }
            mDialog.setContentView(getView());

            mDialog.setCancelable(cancel);
            mDialog.setCanceledOnTouchOutside(cancel);
            return mDialog;
        }

        private View getView() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_custon_dialog, null, false);
            TextView title = (TextView) view.findViewById(R.id.tvTitle);
            TextView content = (TextView) view.findViewById(R.id.tvContent);
            Button postive = (Button) view.findViewById(R.id.btnPositive);
            Button negative = (Button) view.findViewById(R.id.btnNegative);
            if (TextUtils.isEmpty(mTitle)) {
                title.setText(mTitle);
            }
            if (TextUtils.isEmpty(mContent)) {
                content.setText(mContent);
            }
            negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
            postive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPositiveListener != null) {
                        mPositiveListener.onClick(v);
                    }
                }
            });
            return view;
        }
    }
}