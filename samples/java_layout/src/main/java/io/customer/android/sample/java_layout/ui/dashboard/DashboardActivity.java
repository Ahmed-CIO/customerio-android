package io.customer.android.sample.java_layout.ui.dashboard;

import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;

import io.customer.android.sample.java_layout.databinding.ActivityDashboardBinding;
import io.customer.android.sample.java_layout.ui.core.BaseActivity;
import io.customer.android.sample.java_layout.ui.login.LoginActivity;
import io.customer.android.sample.java_layout.ui.user.AuthViewModel;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding> {

    private AuthViewModel authViewModel;

    @Override
    protected ActivityDashboardBinding inflateViewBinding() {
        return ActivityDashboardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void injectDependencies() {
        authViewModel = viewModelProvider.get(AuthViewModel.class);
    }

    @Override
    protected void setupContent() {
        validateAuth();
        setupViews();
        setupObservers();
    }

    private void validateAuth() {
        // Set up an OnPreDrawListener to the root view.
        final View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        Boolean isLoggedIn = authViewModel.getUserLoggedInStateObservable().getValue();
                        if (isLoggedIn == null) {
                            return false;
                        }

                        content.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                }
        );
    }

    private void setupViews() {
        binding.logoutButton.setOnClickListener(view -> {
            authViewModel.clearLoggedInUser();
        });
    }

    private void setupObservers() {
        authViewModel.getUserLoggedInStateObservable().observe(this, isLoggedIn -> {
        });
        authViewModel.getUserDataObservable().observe(this, user -> {
        });
        authViewModel.getUserLoggedInStateObservable().observe(this, isLoggedIn -> {
            if (!isLoggedIn) {
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}