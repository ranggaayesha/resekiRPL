package android.reseki.com.reseki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class RemindToCook extends Fragment {

    Button btnRemindMe;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remind_to_cook,container,false);

        btnRemindMe = (Button) view.findViewById(R.id.remind);

        btnRemindMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
