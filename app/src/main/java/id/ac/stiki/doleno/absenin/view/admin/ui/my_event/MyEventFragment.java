package id.ac.stiki.doleno.absenin.view.admin.ui.my_event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.databinding.FragmentMyEventBinding;

@AndroidEntryPoint
public class MyEventFragment extends Fragment {
    private FragmentMyEventBinding binding;
    private MyEventViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyEventBinding.inflate(inflater, container, false);
        binding.rvMyEvent.setLayoutManager(new LinearLayoutManager(this.getContext()));

        viewModel = new ViewModelProvider(this).get(MyEventViewModel.class);
        viewModel.fetchMyEvent();

        viewModel.myEventState.observeForever(state -> {
            binding.layoutLoading.setVisibility(View.GONE);
            binding.layoutError.setVisibility(View.GONE);
            binding.layoutEmpty.setVisibility(View.GONE);
            binding.rvMyEvent.setVisibility(View.GONE);
            switch (state) {
                case LOADING:
                    binding.layoutLoading.setVisibility(View.VISIBLE);
                    break;
                case FAILED:
                    binding.layoutError.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    if (viewModel.isListEventEmpty()) {
                        binding.layoutEmpty.setVisibility(View.VISIBLE);
                    } else {
                        binding.rvMyEvent.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        });

        viewModel.listMyEvent.observeForever(listEvent -> {
            MyEventAdapter adapter = new MyEventAdapter(listEvent, event -> {
                //navigate to event detail
            });
            binding.rvMyEvent.setAdapter(adapter);
        });

        return binding.getRoot();
    }
}