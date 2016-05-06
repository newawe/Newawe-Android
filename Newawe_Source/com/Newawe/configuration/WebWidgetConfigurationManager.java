package com.Newawe.configuration;

import android.content.Context;
import com.Newawe.Factory;
import com.Newawe.controllers.WidgetsController;
import com.Newawe.utils.VersionManager;
import java.io.IOException;

public class WebWidgetConfigurationManager {
    private static WebWidgetConfigurationManager instance;
    private WebWidgetConfiguration config;
    private XMLConfigurationParser parser;

    /* renamed from: com.Newawe.configuration.WebWidgetConfigurationManager.1 */
    class C02171 implements Runnable {
        final /* synthetic */ Context val$context;
        final /* synthetic */ WebWidgetConfiguration val$webWidgetConfiguration;
        final /* synthetic */ WidgetsController val$widgetsController;

        C02171(Context context, WebWidgetConfiguration webWidgetConfiguration, WidgetsController widgetsController) {
            this.val$context = context;
            this.val$webWidgetConfiguration = webWidgetConfiguration;
            this.val$widgetsController = widgetsController;
        }

        public void run() {
            try {
                ObjectSerializer<WebWidgetConfiguration> configSerializer = new ObjectSerializer(this.val$context);
                ObjectSerializer<WidgetsController> widgetsSerializer = new ObjectSerializer(this.val$context);
                configSerializer.serializeAndSaveObject(this.val$webWidgetConfiguration, "webWidgetConfiguration");
                widgetsSerializer.serializeAndSaveObject(this.val$widgetsController, "widgetsController");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static WebWidgetConfigurationManager getInstance(Context context) {
        if (instance == null) {
            instance = new WebWidgetConfigurationManager(context);
        }
        return instance;
    }

    public void destroy() {
        instance = null;
    }

    private WebWidgetConfigurationManager(Context context) {
        this.parser = new XMLConfigurationParser(context);
    }

    public WebWidgetConfiguration loadConfiguration(Context context) throws Exception {
        int currentVersion = VersionManager.getCurrentVersion(context);
        int previousVersion = VersionManager.getPreviousVersion(context);
        if (currentVersion != previousVersion || previousVersion == -1) {
            this.config = loadFromCurrentConfig(context);
            VersionManager.updateVersion(context, currentVersion);
        } else {
            try {
                this.config = loadSerializedConfiguration(context);
            } catch (Exception e) {
                this.config = loadFromCurrentConfig(context);
            }
        }
        this.config.loadGuid(context);
        this.config.loadPushAccount(context);
        return this.config;
    }

    private WebWidgetConfiguration loadFromCurrentConfig(Context context) {
        try {
            WebWidgetConfiguration config = this.parser.parse();
            saveConfiguration(config, Factory.getInstance().getWidgetsController(), context);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WebWidgetConfiguration loadSerializedConfiguration(Context context) throws IOException, ClassNotFoundException {
        ObjectSerializer<WebWidgetConfiguration> configSerializer = new ObjectSerializer(context);
        Factory.getInstance().setWidgetsController((WidgetsController) new ObjectSerializer(context).loadSerializedObject("widgetsController"));
        return (WebWidgetConfiguration) configSerializer.loadSerializedObject("webWidgetConfiguration");
    }

    public void saveConfiguration(WebWidgetConfiguration webWidgetConfiguration, WidgetsController widgetsController, Context context) {
        new Thread(new C02171(context, webWidgetConfiguration, widgetsController)).run();
    }
}
