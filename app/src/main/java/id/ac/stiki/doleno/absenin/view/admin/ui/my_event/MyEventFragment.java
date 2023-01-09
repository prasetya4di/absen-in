package id.ac.stiki.doleno.absenin.view.admin.ui.my_event;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.stiki.doleno.absenin.databinding.FragmentMyEventBinding;
import id.ac.stiki.doleno.absenin.view.add_event.AddEventActivity;
import id.ac.stiki.doleno.absenin.view.admin.ui.my_event_detail.MyEventDetailActivity;

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
                        MyEventAdapter adapter = new MyEventAdapter(viewModel.listMyEvent, event -> {
                            Intent intent = new Intent(this.getActivity(), MyEventDetailActivity.class);
                            intent.putExtra("selected_event", (Parcelable) event);
                            startActivity(intent);
                        });
                        binding.rvMyEvent.setAdapter(adapter);
                        binding.rvMyEvent.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        });

        binding.btnAddEvent.setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), AddEventActivity.class);
            startActivity(intent);
        });

        return binding.getRoot();
    }
}