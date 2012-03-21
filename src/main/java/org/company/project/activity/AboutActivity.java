package org.company.project.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.actionbarcompat.RoboActionBarActivity;
import org.company.project.MyApplication;
import org.company.project.R;
import roboguice.inject.InjectView;

import javax.inject.Inject;

/**
 *
 * @author jcampbell
 */

public class AboutActivity extends RoboActionBarActivity {
    public static final String TAG = MyApplication.createTag(Activity.class);

    @Inject
    private MyApplication myApplication;

    @InjectView(R.id.version_info)
    private TextView versionTextView;

    public static enum LinkTypes {

        PRIVACY_POLICY,
        TERMS_OF_USE,
        LICENSE_AGREEMENT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        versionTextView.setText(getVersionName());
    }

    private String getVersionName() {
        String versionString = "--not found--";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo("org.company.project", PackageManager.GET_META_DATA);
            versionString = pInfo.versionName + " (" + myApplication.readBuildNumber() + ")";
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "Cannon find version name");
        }
        return versionString;
    }
}
