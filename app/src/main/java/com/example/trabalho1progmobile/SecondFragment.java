package com.example.trabalho1progmobile;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.trabalho1progmobile.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentSecondBinding binding;
    private String selectedSound;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
//        binding.spinnerSound.setOnItemSelectedListener(this);
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        Spinner spinner = (Spinner) binding.soundSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sounds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        binding.soundSpinner.setOnItemSelectedListener(this);
        binding.buttonSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer Sound = MediaPlayer.create(getActivity(), getSound(selectedSound));
                Sound.start();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedSound = binding.soundSpinner.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedSound = new String ("Iha");
    }

    public int getSound(String sound){
        switch(sound){
            case "Iha":
                return R.raw.iha;
            case "Não é o pai":
                return R.raw.naoeopai;
            case "Rapaz":
                return R.raw.rapaz;
            case "Ratinho":
                return R.raw.ratinho;
            case "Uepa":
                return R.raw.uepa;
            default:
                return 0;
        }
    }
}