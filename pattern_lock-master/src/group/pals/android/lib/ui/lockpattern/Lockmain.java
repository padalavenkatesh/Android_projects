package group.pals.android.lib.ui.lockpattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Lockmain extends Activity {
	
	static LockPatternActivity la=new LockPatternActivity();
	public static String PaternSha1 = "pattern_sha1";
	private SharedPreferences fPrefs;
	String pattern;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fPrefs=getSharedPreferences("LockPatternActivity.class",MODE_PRIVATE);
		PaternSha1 = (fPrefs.getString(PaternSha1, ""));
	
	if(PaternSha1==null){
		//la.doCreatePattern(null);
       Intent in=new Intent(Lockmain.this,LockPatternActivity.class);
       startActivity(in);
	}
	else{
		 Intent in2=new Intent(Lockmain.this,Lockcheck.class);
	       startActivity(in2);
		
		}
	}
}
