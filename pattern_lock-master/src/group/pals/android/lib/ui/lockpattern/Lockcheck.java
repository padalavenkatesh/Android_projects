package group.pals.android.lib.ui.lockpattern;

import group.pals.android.lib.ui.lockpattern.widget.LockPatternUtils;
import group.pals.android.lib.ui.lockpattern.widget.LockPatternView;
import group.pals.android.lib.ui.lockpattern.widget.LockPatternView.Cell;
import group.pals.android.lib.ui.lockpattern.widget.LockPatternView.DisplayMode;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Lockcheck extends Activity {

       public static final String Mode = "mode";
    public static final int CreatePattern = 0;
    public static final int ComparePattern = 1;
    public static final String AutoSave = "auto_save";
    public static final String MaxRetry = "max_retry";

    public static final String PaternSha1 = "pattern_sha1";

    private SharedPreferences fPrefs;
    private int fMode;
    private int fMaxRetry;
    private boolean fAutoSave;

    private TextView fTxtInfo;
    private LockPatternView fLockPatternView;
    private View fFooter;
    private Button fBtnCancel;
    private Button fBtnConfirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_pattern_activity);

        fPrefs = getSharedPreferences(LockPatternActivity.class.getSimpleName(), 0);

        fMode = getIntent().getIntExtra(Mode, CreatePattern);
        fMaxRetry = getIntent().getIntExtra(MaxRetry, 5);
        fAutoSave = getIntent().getBooleanExtra(AutoSave, true);

        fTxtInfo = (TextView) findViewById(R.id.lpa_text_info);
        fLockPatternView = (LockPatternView) findViewById(R.id.lpa_lockPattern);

        fFooter = findViewById(R.id.lpa_layout_footer);
        fBtnCancel = (Button) findViewById(R.id.lpa_button_cancel);
        fBtnConfirm = (Button) findViewById(R.id.lpa_button_confirm);

        init();
    }// onCreate()

    private void init() {
        fLockPatternView.setOnPatternListener(fPatternViewListener);

        switch (fMode) {
        case CreatePattern:
            fBtnCancel.setOnClickListener(fBtnCancelOnClickListener);
            fBtnConfirm.setOnClickListener(fBtnConfirmOnClickListener);

            fFooter.setVisibility(View.VISIBLE);
            fTxtInfo.setText(R.string.msg_draw_an_unlock_pattern);
            break;
      /*  case ComparePattern:
            fFooter.setVisibility(View.GONE);
            fTxtInfo.setText(R.string.msg_draw_pattern_to_unlock);
            break;*/
        }

        setResult(RESULT_CANCELED);
    }// init()

    private int retryCount = 0;

/*    void doComparePattern(String pattern) {
        if (pattern == null)
            return;

        String currentPattern = getIntent().getStringExtra(PaternSha1);
        if (currentPattern == null)
            currentPattern = fPrefs.getString(PaternSha1, null);

        if (pattern.equals(currentPattern)) {
            setResult(RESULT_OK);
            finish();
        } else {
            retryCount++;

            if (retryCount >= fMaxRetry) {
                setResult(RESULT_CANCELED);
                finish();
            } else {
                fLockPatternView.setDisplayMode(DisplayMode.Wrong);
                fTxtInfo.setText(R.string.msg_try_again);
            }
        }
    }// doComparePattern()
*/
    private String lastPattern;

    private void doCreatePattern(List<Cell> pattern) {
        if (pattern.size() < 4) {
            fLockPatternView.setDisplayMode(DisplayMode.Wrong);
            fTxtInfo.setText(R.string.msg_connect_4dots);
            return;
        }

        if (lastPattern == null) {
            lastPattern = LockPatternUtils.patternToSha1(pattern);
            fTxtInfo.setText(R.string.msg_pattern_recorded);
            fBtnConfirm.setEnabled(true);
        } else {
            if (lastPattern.equals(LockPatternUtils.patternToSha1(pattern))) {
                fTxtInfo.setText(R.string.msg_your_new_unlock_pattern);
                fBtnConfirm.setEnabled(true);
            } else {
                fTxtInfo.setText(R.string.msg_redraw_pattern_to_confirm);
                fBtnConfirm.setEnabled(false);
                fLockPatternView.setDisplayMode(DisplayMode.Wrong);
            }
        }
    }// doCreatePattern()

    private final LockPatternView.OnPatternListener fPatternViewListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            fLockPatternView.setDisplayMode(DisplayMode.Correct);

            if (fMode == CreatePattern) {
                fTxtInfo.setText(R.string.msg_release_finger_when_done);
                fBtnConfirm.setEnabled(false);
                if (getString(R.string.cmd_continue).equals(
                        fBtnConfirm.getText()))
                    lastPattern = null;
            }
        }

        @Override
        public void onPatternDetected(List<Cell> pattern) {
            switch (fMode) {
            case CreatePattern:
                doCreatePattern(pattern);
                break;
            /*case ComparePattern:
                doComparePattern(LockPatternUtils.patternToSha1(pattern));
                break;*/
            }
        }// onPatternDetected()

        @Override
        public void onPatternCleared() {
            fLockPatternView.setDisplayMode(DisplayMode.Correct);

            switch (fMode) {
            case CreatePattern:
                fBtnConfirm.setEnabled(false);
                if (getString(R.string.cmd_continue).equals(
                        fBtnConfirm.getText())) {
                    lastPattern = null;
                    fTxtInfo.setText(R.string.msg_draw_an_unlock_pattern);
                } else
                    fTxtInfo.setText(R.string.msg_redraw_pattern_to_confirm);
                break;
            /*case ComparePattern:
                fTxtInfo.setText(R.string.msg_draw_pattern_to_unlock);
                break;*/
            }
        }// onPatternCleared()

        @Override
        public void onPatternCellAdded(List<Cell> pattern) {
            // TODO Auto-generated method stub
        }
    };// fPatternViewListener

    private final View.OnClickListener fBtnCancelOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };// fBtnCancelOnClickListener

    private final View.OnClickListener fBtnConfirmOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (getString(R.string.cmd_continue).equals(fBtnConfirm.getText())) {
                fLockPatternView.clearPattern();
                fTxtInfo.setText(R.string.msg_redraw_pattern_to_confirm);
                fBtnConfirm.setText(R.string.cmd_confirm);
                fBtnConfirm.setEnabled(false);
                 } else {
                if (fAutoSave)
                    fPrefs.edit().putString(PaternSha1, lastPattern).commit();

                Intent i = new Intent();
                i.putExtra(PaternSha1, lastPattern);
                setResult(RESULT_OK, i);
                Toast.makeText(Lockcheck.this, "call here to main screen ", Toast.LENGTH_LONG).show();
                
                finish();
            }
        }
    };// fBtnConfirmOnClickListener
}
