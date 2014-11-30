package eu.angrysoft.gaderypoluki;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText editTxt;
	private TextView textView;
	private Spinner spinnerCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button btn_encrypt = (Button) findViewById(R.id.button1);
		final Button btn_decrypt = (Button) findViewById(R.id.button3);
		final Button btn_clean = (Button) findViewById(R.id.button2);
		this.editTxt = (EditText) findViewById(R.id.editText1);
		this.textView = (TextView) findViewById(R.id.TextView1);
		this.textView.setText("...");
		spinnerCode = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.code_spinner_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerCode.setAdapter(adapter);
		
		
		btn_encrypt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				textView.setText(encrypt(editTxt.getText().toString()));
			}
		});
		
		btn_decrypt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView.setText(encrypt(editTxt.getText().toString()));
			}
		});
		
		btn_clean.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clean();
				
			}
		});
	}
	
	protected void clean() {
		this.textView.setText("...");
		this.editTxt.setText("");
	}
	
	protected String getCode() {
		//long itemCode = spinnerCode.getSelectedItemId();
		String code =spinnerCode.getSelectedItem().toString();
		code = code.toLowerCase(Locale.getDefault());
		code = code.replaceAll("-", "");
		return code;
	}

	@SuppressLint("DefaultLocale")
	protected String encrypt(String txtToEncrypt) {
		String code = getCode();
		txtToEncrypt = txtToEncrypt.toLowerCase(Locale.getDefault());
		String encryptedText = "";
		for (char c : txtToEncrypt.toCharArray() ) {
			int i = code.indexOf(c) + 1;
			if (i > 0) {
				if (i % 2 == 0 ) {
					--i;
				} else {
					++i;
				}
				c = code.charAt(i-1);
			}
			encryptedText += c;
		}
		
		return encryptedText;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
