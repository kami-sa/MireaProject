package ru.mirea.saidova.mireaproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private TextView resultField; // текстовое поле для вывода результата
    private EditText numberField1, numberField2;
    private RadioButton rbPlus, rbMinus, rbMulty, rbDiv;
    private Button btnRes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //numberField = findViewById(R.id.num1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_calculate2, container, false);
        View view = inflater.inflate(R.layout.fragment_calculate2,
                container, false);
        numberField1 = view.findViewById(R.id.num1);
        numberField2 = view.findViewById(R.id.num2);
        rbPlus = view.findViewById(R.id.radioButtonPlus);
        rbMinus = view.findViewById(R.id.radioButtonMinus);
        rbMulty = view.findViewById(R.id.radioButtonMulti);
        rbDiv = view.findViewById(R.id.radioButtonDiv);
        btnRes = view.findViewById(R.id.btnRes);
        btnRes.setOnClickListener(this);
        resultField = view.findViewById(R.id.textViewRes);

        // Button nextButton = (Button) view.findViewById(R.id.button_first);
        //nextButton.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //@Override
    public void onClick(View view)
    {
        calculation();
    }

    public void calculation ()
    {
        int n1 = Integer.parseInt(numberField1.getText().toString());
        int n2 = Integer.parseInt(numberField2.getText().toString());

        if (rbPlus.isChecked())
        {
            Integer res = n1 + n2;
            resultField.setText(res.toString());
        }
        if (rbMinus.isChecked())
        {
            Integer res = n1 - n2;
            resultField.setText(res.toString());
        }
        if (rbMulty.isChecked())
        {
            Integer res = n1 * n2;
            resultField.setText(res.toString());
        }
        if (rbDiv.isChecked())
        {
            Integer res = n1 / n2;
            resultField.setText(res.toString());
        }
    }
}
