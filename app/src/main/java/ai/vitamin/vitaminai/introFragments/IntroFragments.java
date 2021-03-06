package ai.vitamin.vitaminai.introFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ai.vitamin.vitaminai.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link IntroFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntroFragments extends Fragment {
    EditText height;
    EditText weight;
    EditText age;
    Button submit;

    public IntroFragments() {
    }

    public static IntroFragments newInstance() {
        return new IntroFragments();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_intro_fragments, container, false);

        height = height.findViewById(R.id.textHeight);
        weight = weight.findViewById(R.id.textWeight);
        age = age.findViewById(R.id.textAge);
        submit = submit.findViewById(R.id.submit);
        //final SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sHeight = height.getText().toString();
                String sWeight = weight.getText().toString();
                String sAge = age.getText().toString();

                SharedPreferences sharedPref = getActivity().getSharedPreferences("userData",Context.MODE_PRIVATE);
              //  double sHeight = sharedPref.getFloat("sHeight", )
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putFloat("height",Float.parseFloat(sHeight));
                editor.putFloat("weight",Float.parseFloat(sWeight));
                editor.putInt("age", Integer.parseInt(sAge));

                editor.apply();

                height.setText(sHeight);
                weight.setText(sWeight);
                age.setText(sAge);

                SharedPreferences settings = getActivity().getSharedPreferences("prefs", 0);
                SharedPreferences.Editor editorSettings = settings.edit();
                editorSettings.putBoolean("firstRun", false);
                editorSettings.apply();

                boolean firstRun = settings.getBoolean("firstRun", true);
                Log.d("TAG1", "firstRun: " + Boolean.valueOf(firstRun).toString());
            }
        });

        return view;
    }

}

