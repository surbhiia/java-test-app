package com.test.javatestapplication;

import static io.opentelemetry.api.common.AttributeKey.longKey;
import static io.opentelemetry.api.common.AttributeKey.stringKey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

//import com.splunk.rum.SplunkRum;
import com.splunk.rum.integration.agent.api.SplunkRum;
import com.test.javatestapplication.databinding.FragmentFirstBinding;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //SplunkRum.getInstance().addRumEvent("first fragment created", Attributes.of(stringKey("one"), "1", longKey("two"), 2L));

       /* Span workflowSpan = SplunkRum.getInstance().startWorkflow("Test Workflow");
        if (workflowSpan != null) {
            workflowSpan.setAttribute("workflow.attribute1", "value1");
            workflowSpan.setAttribute("workflow.attribute2", "value2");
            workflowSpan.end();
        }*/

        //SplunkRum.getInstance().addRumException(new Throwable("This is a custom throwable"));

        //SplunkRum.getInstance().addRumException(new Throwable("This is a custom throwable"), Attributes.of(stringKey("one"), "1", longKey("two"), 2L));

        binding.buttonFirst.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

        binding.buttonSecond.setOnClickListener(v ->
                triggerANR()
        );

        binding.buttonThird.setOnClickListener(v ->
                simpleCrash()
        );
    }

    private void simpleCrash() {
        throw new RuntimeException("Simple RuntimeException Crash");
    }

    private void triggerANR() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}