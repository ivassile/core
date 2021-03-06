package org.jboss.as.console.client.analytics;

import com.google.gwt.core.client.GWT;
import com.google.inject.Provider;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalytics;
import org.jboss.as.console.client.ProductConfig;
import org.jboss.as.console.client.shared.Preferences;

import javax.inject.Inject;

/**
 * @author Heiko Braun
 * @date 10/24/12
 */
public class AnalyticsProvider implements Provider<GoogleAnalytics> {

    private static final GoogleAnalytics NOOP = new NoopAnalytics();

    private ProductConfig prodConfig;

    @Inject
    public AnalyticsProvider(ProductConfig prodConfig) {
        this.prodConfig = prodConfig;
    }

    @Override
    public GoogleAnalytics get() {

        GoogleAnalytics analytics;


        if(!Preferences.has(Preferences.Key.ANALYTICS)) // inital setup
        {
            // in Community we enable analytics by default
            boolean isCommunity = ProductConfig.Profile.COMMUNITY.equals(prodConfig.getProfile());

            if (isCommunity)
                Preferences.set(Preferences.Key.ANALYTICS, "true");
            else
                Preferences.set(Preferences.Key.ANALYTICS, "false");
        }

        // check settings if enabled
        boolean isEnabledInPreferences = Preferences.has(Preferences.Key.ANALYTICS) && Preferences
                .get(Preferences.Key.ANALYTICS).equals("true");

        // Google Analytics is an opt-in for the product and an opt-out for the community version
        // in web mode it's always enabled (disabled during development)
        boolean isWebMode = GWT.isScript();
        if (isWebMode) {
            analytics = isEnabledInPreferences ? new CustomAnalyticsImpl() : NOOP;
        } else {
            analytics = NOOP;
        }

        System.out.println("Google analytics: Using " + (analytics == NOOP ? "stub" : "real") + " implementation");

        return analytics;
    }


    static class NoopAnalytics implements GoogleAnalytics {

        @Override
        public void init(String userAccount) {}

        @Override
        public void addAccount(String trackerName, String userAccount) {}

        @Override
        public void trackPageview() {}

        @Override
        public void trackPageview(String pageName) {}

        @Override
        public void trackPageview(String trackerName, String pageName) {}

        @Override
        public void trackEvent(String category, String action) {}

        @Override
        public void trackEventWithTracker(String trackerName, String category, String action) {}

        @Override
        public void trackEvent(String category, String action, String optLabel) {}

        @Override
        public void trackEventWithTracker(String trackerName, String category, String action, String optLabel) {}

        @Override
        public void trackEvent(String category, String action, String optLabel, int optValue) {}

        @Override
        public void trackEventWithTracker(String trackerName, String category, String action, String optLabel,
                int optValue) {}

        @Override
        public void trackEvent(String category, String action, String optLabel, int optValue,
                boolean optNonInteraction) {}

        @Override
        public void trackEventWithTracker(String trackerName, String category, String action, String optLabel,
                int optValue, boolean optNonInteraction) {}
    }
}
