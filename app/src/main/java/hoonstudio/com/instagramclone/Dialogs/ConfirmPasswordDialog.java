package hoonstudio.com.instagramclone.Dialogs;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import hoonstudio.com.instagramclone.R;

import static android.content.ContentValues.TAG;

/**
 * Created by joon on 1/15/2018.
 */

public class ConfirmPasswordDialog extends DialogFragment {
    private static final String TAG = "ConfirmPasswordDialog";

    TextView mPassword;

    public interface OnConfirmPasswordListener{
        void onConfirmPassword(String password);
    }
    OnConfirmPasswordListener mOnConfirmPasswordListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm_password, container, false);
        mPassword = (TextView) view.findViewById(R.id.confirm_password);
        Log.d(TAG, "onCreateView: started");

        TextView cancelDialog = (TextView) view.findViewById(R.id.dialogCancel);
        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: closing the dialog");
                getDialog().dismiss();
            }
        });

        TextView confirmDialog = (TextView) view.findViewById(R.id.dialogConfirm);
        confirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: confirming the dialog");

                String password = mPassword.getText().toString();
                if(!password.equals("")){
                    mOnConfirmPasswordListener.onConfirmPassword(password);
                    getDialog().dismiss();
                }else{
                    Toast.makeText(getActivity(), "You must enter your password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mOnConfirmPasswordListener = (OnConfirmPasswordListener) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
