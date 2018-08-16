package com.example.ero.async_task_progress;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String KAY = "kay";
    public static final String KAY_FR = "kay_fr";
    private AsyncDialogFragment dialogFragment;
    private FragmentManager fragment;
    private EditText editText;
    private TextView endtext;
    private TextView textsecond;
    private TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button_id);
        textsecond = findViewById(R.id.second);
        endtext = findViewById(R.id.text_end);
        editText = findViewById(R.id.edit_id);
        num = findViewById(R.id.end_second);
        dialogFragment = new AsyncDialogFragment();
        fragment = this.getFragmentManager();

        enterProgress(button);
    }

    private void enterProgress(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressAsyncTask myTask = new ProgressAsyncTask();
                if (!editText.getText().toString().isEmpty()) {
                    myTask.execute();
                } else {
                    Toast.makeText(MainActivity.this, R.string.enter, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class ProgressAsyncTask extends AsyncTask<Void, Void, Void> {
        private String second = editText.getText().toString();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(Integer.valueOf(second));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            final Bundle bundle = new Bundle();
            bundle.putString(KAY_FR, second);
            dialogFragment.setArguments(bundle);
            dialogFragment.show(fragment, KAY);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            endtext.setVisibility(View.VISIBLE);
            textsecond.setVisibility(View.VISIBLE);
            num.setText(second);
            dialogFragment.dismiss();
        }
    }
}
