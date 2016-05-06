package com.google.android.youtube.player.internal;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.google.android.youtube.player.internal.x */
public final class C0677x {
    public static Map<String, String> m968a(Locale locale) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("error_initializing_player", "An error occurred while initializing the YouTube player.");
        hashMap.put("get_youtube_app_title", "Get YouTube App");
        hashMap.put("get_youtube_app_text", "This app won't run without the YouTube App, which is missing from your device");
        hashMap.put("get_youtube_app_action", "Get YouTube App");
        hashMap.put("enable_youtube_app_title", "Enable YouTube App");
        hashMap.put("enable_youtube_app_text", "This app won't work unless you enable the YouTube App.");
        hashMap.put("enable_youtube_app_action", "Enable YouTube App");
        hashMap.put("update_youtube_app_title", "Update YouTube App");
        hashMap.put("update_youtube_app_text", "This app won't work unless you update the YouTube App.");
        hashMap.put("update_youtube_app_action", "Update YouTube App");
        C0677x.m969a(hashMap, locale.getLanguage());
        String valueOf = String.valueOf(locale.getLanguage());
        String valueOf2 = String.valueOf(locale.getCountry());
        C0677x.m969a(hashMap, new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("_").append(valueOf2).toString());
        return hashMap;
    }

    private static void m969a(Map<String, String> map, String str) {
        if ("af".equals(str)) {
            map.put("error_initializing_player", "Kon nie die YouTube-speler inisialiseer nie.");
            map.put("get_youtube_app_title", "Kry YouTube-program");
            map.put("get_youtube_app_text", "Hierdie program sal nie loop sonder die YouTube-program, wat ontbreek van jou toestel, nie");
            map.put("get_youtube_app_action", "Kry YouTube-program");
            map.put("enable_youtube_app_title", "Aktiveer YouTube-program");
            map.put("enable_youtube_app_text", "Hierdie program sal nie werk tensy jy die YouTube-program aktiveer nie.");
            map.put("enable_youtube_app_action", "Aktiveer YouTube-program");
            map.put("update_youtube_app_title", "Dateer YouTube-program op");
            map.put("update_youtube_app_text", "Hierdie program sal nie werk tensy jy die YouTube-program opdateer nie.");
            map.put("update_youtube_app_action", "Dateer YouTube-program op");
        } else if ("am".equals(str)) {
            map.put("error_initializing_player", "\u12e8YouTube \u12a0\u132b\u12cb\u1279\u1295 \u1260\u121b\u1235\u1300\u1218\u122d \u120b\u12ed \u1233\u1208 \u12a0\u1295\u12f5 \u1235\u1205\u1270\u1275 \u1270\u12a8\u1235\u1277\u120d\u1362");
            map.put("get_youtube_app_title", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12eb\u130d\u1299");
            map.put("get_youtube_app_text", "\u12ed\u1205 \u1218\u1270\u130d\u1260\u122a\u12eb \u12eb\u1208 YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd \u12a0\u12ed\u1202\u12f5\u121d\u1363 \u12a5\u1231 \u12f0\u130d\u121e \u1260\u1218\u1223\u122a\u12eb\u12ce \u120b\u12ed \u12e8\u1208\u121d\u1362");
            map.put("get_youtube_app_action", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12eb\u130d\u1299");
            map.put("enable_youtube_app_title", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12eb\u1295\u1241");
            map.put("enable_youtube_app_text", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12a5\u1235\u12ab\u120b\u1290\u1241 \u12f5\u1228\u1235 \u12ed\u1205 \u1218\u1270\u130d\u1260\u122a\u12eb \u12a0\u12ed\u1230\u122b\u121d\u1362");
            map.put("enable_youtube_app_action", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12eb\u1295\u1241");
            map.put("update_youtube_app_title", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12eb\u12d8\u121d\u1291");
            map.put("update_youtube_app_text", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12a5\u1235\u12ab\u120b\u12d8\u1218\u1291\u1275 \u12f5\u1228\u1235 \u12ed\u1205 \u1218\u1270\u130d\u1260\u122a\u12eb \u12a0\u12ed\u1230\u122b\u121d\u1362");
            map.put("update_youtube_app_action", "\u12e8YouTube \u1218\u1270\u130d\u1260\u122a\u12eb\u12cd\u1295 \u12eb\u12d8\u121d\u1291");
        } else if ("ar".equals(str)) {
            map.put("error_initializing_player", "\u062d\u062f\u062b \u062e\u0637\u0623 \u0623\u062b\u0646\u0627\u0621 \u062a\u0647\u064a\u0626\u0629 \u0645\u0634\u063a\u0644 YouTube.");
            map.put("get_youtube_app_title", "\u0627\u0644\u062d\u0635\u0648\u0644 \u0639\u0644\u0649 \u062a\u0637\u0628\u064a\u0642 YouTube");
            map.put("get_youtube_app_text", "\u0644\u0646 \u064a\u0639\u0645\u0644 \u0647\u0630\u0627 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0628\u062f\u0648\u0646 \u062a\u0637\u0628\u064a\u0642 YouTube \u0627\u0644\u0630\u064a \u0644\u0627 \u064a\u062a\u0648\u0641\u0631 \u0639\u0644\u0649 \u062c\u0647\u0627\u0632\u0643");
            map.put("get_youtube_app_action", "\u0627\u0644\u062d\u0635\u0648\u0644 \u0639\u0644\u0649 \u062a\u0637\u0628\u064a\u0642 YouTube");
            map.put("enable_youtube_app_title", "\u062a\u0645\u0643\u064a\u0646 \u062a\u0637\u0628\u064a\u0642 YouTube");
            map.put("enable_youtube_app_text", "\u0644\u0646 \u064a\u0639\u0645\u0644 \u0647\u0630\u0627 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0645\u0627 \u0644\u0645 \u064a\u062a\u0645 \u062a\u0645\u0643\u064a\u0646 \u062a\u0637\u0628\u064a\u0642 YouTube.");
            map.put("enable_youtube_app_action", "\u062a\u0645\u0643\u064a\u0646 \u062a\u0637\u0628\u064a\u0642 YouTube");
            map.put("update_youtube_app_title", "\u062a\u062d\u062f\u064a\u062b \u062a\u0637\u0628\u064a\u0642 YouTube");
            map.put("update_youtube_app_text", "\u0644\u0646 \u064a\u0639\u0645\u0644 \u0647\u0630\u0627 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0645\u0627 \u0644\u0645 \u064a\u062a\u0645 \u062a\u062d\u062f\u064a\u062b \u062a\u0637\u0628\u064a\u0642 YouTube.");
            map.put("update_youtube_app_action", "\u062a\u062d\u062f\u064a\u062b \u062a\u0637\u0628\u064a\u0642 YouTube");
        } else if ("be".equals(str)) {
            map.put("error_initializing_player", "\u041f\u0430\u043c\u044b\u043b\u043a\u0430 \u043f\u0430\u0434\u0447\u0430\u0441 \u0456\u043d\u0456\u0446\u044b\u044f\u043b\u0456\u0437\u0430\u0446\u044b\u0456 \u043f\u0440\u0430\u0439\u0433\u0440\u0430\u0432\u0430\u043b\u044c\u043di\u043a\u0430 YouTube.");
            map.put("get_youtube_app_title", "\u0421\u043f\u0430\u043c\u043f\u0430\u0432\u0430\u0446\u044c \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube");
            map.put("get_youtube_app_text", "\u0413\u044d\u0442\u0430 \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 \u043d\u0435 \u0431\u0443\u0434\u0437\u0435 \u043f\u0440\u0430\u0446\u0430\u0432\u0430\u0446\u044c \u0431\u0435\u0437 \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u044f YouTube, \u044f\u043a\u043e\u0435 \u0430\u0434\u0441\u0443\u0442\u043d\u0456\u0447\u0430\u0435 \u043d\u0430 \u043f\u0440\u044b\u043b\u0430\u0434\u0437\u0435");
            map.put("get_youtube_app_action", "\u0421\u043f\u0430\u043c\u043f\u0430\u0432\u0430\u0446\u044c \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube");
            map.put("enable_youtube_app_title", "\u0423\u043a\u043b\u044e\u0447\u044b\u0446\u044c \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube");
            map.put("enable_youtube_app_text", "\u0413\u044d\u0442\u0430 \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 \u043d\u0435 \u0431\u0443\u0434\u0437\u0435 \u043f\u0440\u0430\u0446\u0430\u0432\u0430\u0446\u044c, \u043f\u0430\u043a\u0443\u043b\u044c \u0432\u044b \u043d\u0435 \u045e\u043a\u043b\u044e\u0447\u044b\u0446\u0435 \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube.");
            map.put("enable_youtube_app_action", "\u0423\u043a\u043b\u044e\u0447\u044b\u0446\u044c \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube");
            map.put("update_youtube_app_title", "\u0410\u0431\u043d\u0430\u0432i\u0446\u044c \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube");
            map.put("update_youtube_app_text", "\u0413\u044d\u0442\u0430 \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 \u043d\u0435 \u0431\u0443\u0434\u0437\u0435 \u043f\u0440\u0430\u0446\u0430\u0432\u0430\u0446\u044c, \u043f\u0430\u043a\u0443\u043b\u044c \u0432\u044b \u043d\u0435 \u0430\u0431\u043d\u0430\u0432i\u0446\u0435 \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube.");
            map.put("update_youtube_app_action", "\u0410\u0431\u043d\u0430\u0432i\u0446\u044c \u043f\u0440\u044b\u043a\u043b\u0430\u0434\u0430\u043d\u043d\u0435 YouTube");
        } else if ("bg".equals(str)) {
            map.put("error_initializing_player", "\u041f\u0440\u0438 \u043f\u043e\u0434\u0433\u043e\u0442\u0432\u044f\u043d\u0435\u0442\u043e \u043d\u0430 \u043f\u043b\u0435\u0439\u044a\u0440\u0430 \u043d\u0430 YouTube \u0437\u0430 \u0440\u0430\u0431\u043e\u0442\u0430 \u0432\u044a\u0437\u043d\u0438\u043a\u043d\u0430 \u0433\u0440\u0435\u0448\u043a\u0430.");
            map.put("get_youtube_app_title", "\u0418\u0437\u0442\u0435\u0433\u043b. \u043d\u0430 \u043f\u0440\u0438\u043b. YouTube");
            map.put("get_youtube_app_text", "\u0422\u043e\u0432\u0430 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u043d\u044f\u043c\u0430 \u0434\u0430 \u0440\u0430\u0431\u043e\u0442\u0438 \u0431\u0435\u0437 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435\u0442\u043e YouTube, \u043a\u043e\u0435\u0442\u043e \u043b\u0438\u043f\u0441\u0432\u0430 \u043d\u0430 \u0443\u0441\u0442\u0440\u043e\u0439\u0441\u0442\u0432\u043e\u0442\u043e \u0432\u0438");
            map.put("get_youtube_app_action", "\u0418\u0437\u0442\u0435\u0433\u043b. \u043d\u0430 \u043f\u0440\u0438\u043b. YouTube");
            map.put("enable_youtube_app_title", "\u0410\u043a\u0442. \u043d\u0430 \u043f\u0440\u0438\u043b. YouTube");
            map.put("enable_youtube_app_text", "\u0422\u043e\u0432\u0430 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u043d\u044f\u043c\u0430 \u0434\u0430 \u0440\u0430\u0431\u043e\u0442\u0438, \u043e\u0441\u0432\u0435\u043d \u0430\u043a\u043e \u043d\u0435 \u0430\u043a\u0442\u0438\u0432\u0438\u0440\u0430\u0442\u0435 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435\u0442\u043e YouTube.");
            map.put("enable_youtube_app_action", "\u0410\u043a\u0442. \u043d\u0430 \u043f\u0440\u0438\u043b. YouTube");
            map.put("update_youtube_app_title", "\u0410\u043a\u0442\u0443\u043b. \u043d\u0430 \u043f\u0440\u0438\u043b. YouTube");
            map.put("update_youtube_app_text", "\u0422\u043e\u0432\u0430 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u043d\u044f\u043c\u0430 \u0434\u0430 \u0440\u0430\u0431\u043e\u0442\u0438, \u043e\u0441\u0432\u0435\u043d \u0430\u043a\u043e \u043d\u0435 \u0430\u043a\u0442\u0443\u0430\u043b\u0438\u0437\u0438\u0440\u0430\u0442\u0435 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435\u0442\u043e YouTube.");
            map.put("update_youtube_app_action", "\u0410\u043a\u0442\u0443\u043b. \u043d\u0430 \u043f\u0440\u0438\u043b. YouTube");
        } else if ("ca".equals(str)) {
            map.put("error_initializing_player", "S'ha produ\u00eft un error en iniciar el reproductor de YouTube.");
            map.put("get_youtube_app_title", "Obtenci\u00f3 aplicac. YouTube");
            map.put("get_youtube_app_text", "Aquesta aplicaci\u00f3 no funcionar\u00e0 sense l'aplicaci\u00f3 de YouTube, que encara no \u00e9s al dispositiu.");
            map.put("get_youtube_app_action", "Obt\u00e9n l'aplic. de YouTube");
            map.put("enable_youtube_app_title", "Activaci\u00f3 aplic. YouTube");
            map.put("enable_youtube_app_text", "Aquesta aplicaci\u00f3 no funcionar\u00e0 fins que no activis l'aplicaci\u00f3 de YouTube.");
            map.put("enable_youtube_app_action", "Activa aplicaci\u00f3 YouTube");
            map.put("update_youtube_app_title", "Actualitz. aplic. YouTube");
            map.put("update_youtube_app_text", "Aquesta aplicaci\u00f3 no funcionar\u00e0 fins que no actualitzis l'aplicaci\u00f3 de YouTube.");
            map.put("update_youtube_app_action", "Actualitza aplic. YouTube");
        } else if ("cs".equals(str)) {
            map.put("error_initializing_player", "P\u0159i inicializaci p\u0159ehr\u00e1va\u010de YouTube do\u0161lo k chyb\u011b.");
            map.put("get_youtube_app_title", "St\u00e1hn\u011bte aplikaci YouTube");
            map.put("get_youtube_app_text", "Tuto aplikaci nelze spustit bez aplikace YouTube, kterou v za\u0159\u00edzen\u00ed nem\u00e1te nainstalovanou");
            map.put("get_youtube_app_action", "St\u00e1hnout aplikaci YouTube");
            map.put("enable_youtube_app_title", "Aktivujte aplik. YouTube");
            map.put("enable_youtube_app_text", "Ke spu\u0161t\u011bn\u00ed t\u00e9to aplikace je t\u0159eba aktivovat aplikaci YouTube.");
            map.put("enable_youtube_app_action", "Zapnout aplikaci YouTube");
            map.put("update_youtube_app_title", "Aktualizujte apl. YouTube");
            map.put("update_youtube_app_text", "Ke spu\u0161t\u011bn\u00ed t\u00e9to aplikace je t\u0159eba aktualizovat aplikaci YouTube.");
            map.put("update_youtube_app_action", "Aktualizovat apl. YouTube");
        } else if ("da".equals(str)) {
            map.put("error_initializing_player", "Der opstod en fejl under initialisering af YouTube-afspilleren.");
            map.put("get_youtube_app_title", "F\u00e5 YouTube-appen");
            map.put("get_youtube_app_text", "Denne app kan ikke k\u00f8re uden YouTube-appen, som ikke findes p\u00e5 din enhed");
            map.put("get_youtube_app_action", "F\u00e5 YouTube-appen");
            map.put("enable_youtube_app_title", "Aktiv\u00e9r YouTube-appen");
            map.put("enable_youtube_app_text", "Denne app fungerer ikke, medmindre du aktiverer YouTube-appen.");
            map.put("enable_youtube_app_action", "Aktiv\u00e9r YouTube-appen");
            map.put("update_youtube_app_title", "Opdater YouTube-appen");
            map.put("update_youtube_app_text", "Denne app fungerer ikke, hvis du ikke opdaterer YouTube-appen.");
            map.put("update_youtube_app_action", "Opdater YouTube-appen");
        } else if ("de".equals(str)) {
            map.put("error_initializing_player", "Bei der Initialisierung des YouTube-Players ist ein Fehler aufgetreten.");
            map.put("get_youtube_app_title", "YouTube App herunterladen");
            map.put("get_youtube_app_text", "Diese App kann nur ausgef\u00fchrt werden, wenn die YouTube App bereitgestellt ist. Diese ist auf deinem Ger\u00e4t nicht vorhanden.");
            map.put("get_youtube_app_action", "YouTube App herunterladen");
            map.put("enable_youtube_app_title", "YouTube App aktivieren");
            map.put("enable_youtube_app_text", "Diese App funktioniert nur, wenn die YouTube App aktiviert wird.");
            map.put("enable_youtube_app_action", "YouTube App aktivieren");
            map.put("update_youtube_app_title", "YouTube App aktualisieren");
            map.put("update_youtube_app_text", "Diese App funktioniert nur, wenn die YouTube App aktualisiert wird.");
            map.put("update_youtube_app_action", "YouTube App aktualisieren");
        } else if ("el".equals(str)) {
            map.put("error_initializing_player", "\u03a0\u03b1\u03c1\u03bf\u03c5\u03c3\u03b9\u03ac\u03c3\u03c4\u03b7\u03ba\u03b5 \u03c3\u03c6\u03ac\u03bb\u03bc\u03b1 \u03ba\u03b1\u03c4\u03ac \u03c4\u03b7\u03bd \u03c0\u03c1\u03bf\u03b5\u03c4\u03bf\u03b9\u03bc\u03b1\u03c3\u03af\u03b1 \u03c4\u03bf\u03c5 \u03c0\u03c1\u03bf\u03b3\u03c1\u03ac\u03bc\u03bc\u03b1\u03c4\u03bf\u03c2 \u03b1\u03bd\u03b1\u03c0\u03b1\u03c1\u03b1\u03b3\u03c9\u03b3\u03ae\u03c2 \u03c4\u03bf\u03c5 YouTube.");
            map.put("get_youtube_app_title", "\u039b\u03ae\u03c8\u03b7 YouTube");
            map.put("get_youtube_app_text", "\u0394\u03b5\u03bd \u03b5\u03af\u03bd\u03b1\u03b9 \u03b4\u03c5\u03bd\u03b1\u03c4\u03ae \u03b7 \u03b5\u03ba\u03c4\u03ad\u03bb\u03b5\u03c3\u03b7 \u03b1\u03c5\u03c4\u03ae\u03c2 \u03c4\u03b7\u03c2 \u03b5\u03c6\u03b1\u03c1\u03bc\u03bf\u03b3\u03ae\u03c2 \u03c7\u03c9\u03c1\u03af\u03c2 \u03c4\u03b7\u03bd \u03b5\u03c6\u03b1\u03c1\u03bc\u03bf\u03b3\u03ae YouTube, \u03b7 \u03bf\u03c0\u03bf\u03af\u03b1 \u03b1\u03c0\u03bf\u03c5\u03c3\u03b9\u03ac\u03b6\u03b5\u03b9 \u03b1\u03c0\u03cc \u03c4\u03b7 \u03c3\u03c5\u03c3\u03ba\u03b5\u03c5\u03ae \u03c3\u03b1\u03c2");
            map.put("get_youtube_app_action", "\u039b\u03ae\u03c8\u03b7 YouTube");
            map.put("enable_youtube_app_title", "\u0395\u03bd\u03b5\u03c1\u03b3\u03bf\u03c0\u03bf\u03af\u03b7\u03c3\u03b7 YouTube");
            map.put("enable_youtube_app_text", "\u0394\u03b5\u03bd \u03b5\u03af\u03bd\u03b1\u03b9 \u03b4\u03c5\u03bd\u03b1\u03c4\u03ae \u03b7 \u03bb\u03b5\u03b9\u03c4\u03bf\u03c5\u03c1\u03b3\u03af\u03b1 \u03b1\u03c5\u03c4\u03ae\u03c2 \u03c4\u03b7\u03c2 \u03b5\u03c6\u03b1\u03c1\u03bc\u03bf\u03b3\u03ae\u03c2 \u03b5\u03ac\u03bd \u03b4\u03b5\u03bd \u03b5\u03bd\u03b5\u03c1\u03b3\u03bf\u03c0\u03bf\u03b9\u03ae\u03c3\u03b5\u03c4\u03b5 \u03c4\u03b7\u03bd \u03b5\u03c6\u03b1\u03c1\u03bc\u03bf\u03b3\u03ae YouTube.");
            map.put("enable_youtube_app_action", "\u0395\u03bd\u03b5\u03c1\u03b3\u03bf\u03c0\u03bf\u03af\u03b7\u03c3\u03b7 YouTube");
            map.put("update_youtube_app_title", "\u0395\u03bd\u03b7\u03bc\u03ad\u03c1\u03c9\u03c3\u03b7 YouTube");
            map.put("update_youtube_app_text", "\u0394\u03b5\u03bd \u03b5\u03af\u03bd\u03b1\u03b9 \u03b4\u03c5\u03bd\u03b1\u03c4\u03ae \u03b7 \u03bb\u03b5\u03b9\u03c4\u03bf\u03c5\u03c1\u03b3\u03af\u03b1 \u03b1\u03c5\u03c4\u03ae\u03c2 \u03c4\u03b7\u03c2 \u03b5\u03c6\u03b1\u03c1\u03bc\u03bf\u03b3\u03ae\u03c2 \u03b5\u03ac\u03bd \u03b4\u03b5\u03bd \u03b5\u03bd\u03b7\u03bc\u03b5\u03c1\u03ce\u03c3\u03b5\u03c4\u03b5 \u03c4\u03b7\u03bd \u03b5\u03c6\u03b1\u03c1\u03bc\u03bf\u03b3\u03ae YouTube.");
            map.put("update_youtube_app_action", "\u0395\u03bd\u03b7\u03bc\u03ad\u03c1\u03c9\u03c3\u03b7 YouTube");
        } else if ("en_GB".equals(str)) {
            map.put("error_initializing_player", "An error occurred while initialising the YouTube player.");
            map.put("get_youtube_app_title", "Get YouTube App");
            map.put("get_youtube_app_text", "This app won't run without the YouTube App, which is missing from your device");
            map.put("get_youtube_app_action", "Get YouTube App");
            map.put("enable_youtube_app_title", "Enable YouTube App");
            map.put("enable_youtube_app_text", "This app won't work unless you enable the YouTube App.");
            map.put("enable_youtube_app_action", "Enable YouTube App");
            map.put("update_youtube_app_title", "Update YouTube App");
            map.put("update_youtube_app_text", "This app won't work unless you update the YouTube App.");
            map.put("update_youtube_app_action", "Update YouTube App");
        } else if ("es_US".equals(str)) {
            map.put("error_initializing_player", "Se produjo un error al iniciar el reproductor de YouTube.");
            map.put("get_youtube_app_title", "Obtener YouTube");
            map.put("get_youtube_app_text", "Esta aplicaci\u00f3n no se ejecutar\u00e1 sin la aplicaci\u00f3n YouTube, la cual no se instal\u00f3 en tu dispositivo.");
            map.put("get_youtube_app_action", "Obtener YouTube");
            map.put("enable_youtube_app_title", "Activar YouTube");
            map.put("enable_youtube_app_text", "Esta aplicaci\u00f3n no funcionar\u00e1 a menos que actives la aplicaci\u00f3n YouTube.");
            map.put("enable_youtube_app_action", "Activar YouTube");
            map.put("update_youtube_app_title", "Actualizar YouTube");
            map.put("update_youtube_app_text", "Esta aplicaci\u00f3n no funcionar\u00e1 a menos que actualices la aplicaci\u00f3n YouTube.");
            map.put("update_youtube_app_action", "Actualizar YouTube");
        } else if ("es".equals(str)) {
            map.put("error_initializing_player", "Se ha producido un error al iniciar el reproductor de YouTube.");
            map.put("get_youtube_app_title", "Descarga YouTube");
            map.put("get_youtube_app_text", "Esta aplicaci\u00f3n no funcionar\u00e1 sin la aplicaci\u00f3n YouTube, que no est\u00e1 instalada en el dispositivo.");
            map.put("get_youtube_app_action", "Descargar YouTube");
            map.put("enable_youtube_app_title", "Habilita la aplicaci\u00f3n YouTube");
            map.put("enable_youtube_app_text", "Esta aplicaci\u00f3n no funcionar\u00e1 si no habilitas la aplicaci\u00f3n YouTube.");
            map.put("enable_youtube_app_action", "Habilitar YouTube");
            map.put("update_youtube_app_title", "Actualiza YouTube");
            map.put("update_youtube_app_text", "Esta aplicaci\u00f3n no funcionar\u00e1 si no actualizas la aplicaci\u00f3n YouTube.");
            map.put("update_youtube_app_action", "Actualizar YouTube");
        } else if ("et".equals(str)) {
            map.put("error_initializing_player", "YouTube'i m\u00e4ngija l\u00e4htestamisel tekkis viga.");
            map.put("get_youtube_app_title", "YouTube'i rak. hankimine");
            map.put("get_youtube_app_text", "Rakendus ei k\u00e4ivitu ilma YouTube'i rakenduseta ja teie seadmes see praegu puudub");
            map.put("get_youtube_app_action", "Hangi YouTube'i rakendus");
            map.put("enable_youtube_app_title", "YouTube'i rakenduse lubamine");
            map.put("enable_youtube_app_text", "Rakendus ei toimi, kui te ei luba kasutada YouTube'i rakendust.");
            map.put("enable_youtube_app_action", "Luba YouTube'i rakendus");
            map.put("update_youtube_app_title", "V\u00e4rskenda YouTube");
            map.put("update_youtube_app_text", "Rakendus ei toimi enne, kui olete YouTube'i rakendust v\u00e4rskendanud.");
            map.put("update_youtube_app_action", "V\u00e4rsk. YouTube'i rakend.");
        } else if ("fa".equals(str)) {
            map.put("error_initializing_player", "\u0647\u0646\u06af\u0627\u0645 \u0645\u0642\u062f\u0627\u0631\u062f\u0647\u06cc \u0627\u0648\u0644\u06cc\u0647 \u067e\u062e\u0634\u200c\u06a9\u0646\u0646\u062f\u0647 YouTube\u060c \u062e\u0637\u0627\u06cc\u06cc \u0631\u0648\u06cc \u062f\u0627\u062f.");
            map.put("get_youtube_app_title", "\u062f\u0631\u06cc\u0627\u0641\u062a \u0628\u0631\u0646\u0627\u0645\u0647 YouTube");
            map.put("get_youtube_app_text", "\u0627\u06cc\u0646 \u0628\u0631\u0646\u0627\u0645\u0647 \u0628\u062f\u0648\u0646 \u0628\u0631\u0646\u0627\u0645\u0647 YouTube \u06a9\u0647 \u062f\u0631 \u062f\u0633\u062a\u06af\u0627\u0647 \u0634\u0645\u0627 \u0645\u0648\u062c\u0648\u062f \u0646\u06cc\u0633\u062a\u060c \u0627\u062c\u0631\u0627 \u0646\u0645\u06cc\u200c\u0634\u0648\u062f");
            map.put("get_youtube_app_action", "\u062f\u0631\u06cc\u0627\u0641\u062a \u0628\u0631\u0646\u0627\u0645\u0647 YouTube");
            map.put("enable_youtube_app_title", "\u0641\u0639\u0627\u0644 \u06a9\u0631\u062f\u0646 \u0628\u0631\u0646\u0627\u0645\u0647 YouTube");
            map.put("enable_youtube_app_text", "\u0627\u06cc\u0646 \u0628\u0631\u0646\u0627\u0645\u0647 \u062a\u0646\u0647\u0627 \u062f\u0631 \u0635\u0648\u0631\u062a\u06cc \u06a9\u0627\u0631 \u062e\u0648\u0627\u0647\u062f \u06a9\u0631\u062f \u06a9\u0647 \u0628\u0631\u0646\u0627\u0645\u0647 YouTube \u0631\u0627 \u0641\u0639\u0627\u0644 \u06a9\u0646\u06cc\u062f.");
            map.put("enable_youtube_app_action", "\u0641\u0639\u0627\u0644 \u06a9\u0631\u062f\u0646 \u0628\u0631\u0646\u0627\u0645\u0647 YouTube");
            map.put("update_youtube_app_title", "\u0628\u0647\u200c\u0631\u0648\u0632\u0631\u0633\u0627\u0646\u06cc \u0628\u0631\u0646\u0627\u0645\u0647 YouTube");
            map.put("update_youtube_app_text", "\u0627\u06cc\u0646 \u0628\u0631\u0646\u0627\u0645\u0647 \u06a9\u0627\u0631 \u0646\u062e\u0648\u0627\u0647\u062f \u06a9\u0631\u062f \u0645\u06af\u0631 \u0627\u06cc\u0646\u06a9\u0647 \u0628\u0631\u0646\u0627\u0645\u0647 YouTube \u0631\u0627 \u0628\u0647 \u0631\u0648\u0632 \u06a9\u0646\u06cc\u062f.");
            map.put("update_youtube_app_action", "\u0628\u0647\u200c\u0631\u0648\u0632\u0631\u0633\u0627\u0646\u06cc \u0628\u0631\u0646\u0627\u0645\u0647 YouTube");
        } else if ("fi".equals(str)) {
            map.put("error_initializing_player", "Virhe alustettaessa YouTube-soitinta.");
            map.put("get_youtube_app_title", "Hanki YouTube-sovellus");
            map.put("get_youtube_app_text", "T\u00e4m\u00e4 sovellus ei toimi ilman YouTube-sovellusta, joka puuttuu laitteesta.");
            map.put("get_youtube_app_action", "Hanki YouTube-sovellus");
            map.put("enable_youtube_app_title", "Ota YouTube-sov. k\u00e4ytt\u00f6\u00f6n");
            map.put("enable_youtube_app_text", "T\u00e4m\u00e4 sovellus ei toimi, ellet ota YouTube-sovellusta k\u00e4ytt\u00f6\u00f6n.");
            map.put("enable_youtube_app_action", "Ota YouTube-sov. k\u00e4ytt\u00f6\u00f6n");
            map.put("update_youtube_app_title", "P\u00e4ivit\u00e4 YouTube-sovellus");
            map.put("update_youtube_app_text", "T\u00e4m\u00e4 sovellus ei toimi, ellet p\u00e4ivit\u00e4 YouTube-sovellusta.");
            map.put("update_youtube_app_action", "P\u00e4ivit\u00e4 YouTube-sovellus");
        } else if ("fr".equals(str)) {
            map.put("error_initializing_player", "Une erreur s'est produite lors de l'initialisation du lecteur YouTube.");
            map.put("get_youtube_app_title", "T\u00e9l\u00e9charger appli YouTube");
            map.put("get_youtube_app_text", "Cette application ne fonctionnera pas sans l'application YouTube, qui n'est pas install\u00e9e sur votre appareil.");
            map.put("get_youtube_app_action", "T\u00e9l\u00e9charger appli YouTube");
            map.put("enable_youtube_app_title", "Activer l'appli YouTube");
            map.put("enable_youtube_app_text", "Cette application ne fonctionnera que si vous activez l'application YouTube.");
            map.put("enable_youtube_app_action", "Activer l'appli YouTube");
            map.put("update_youtube_app_title", "Mise \u00e0 jour appli YouTube");
            map.put("update_youtube_app_text", "Cette application ne fonctionnera que si vous mettez \u00e0 jour l'application YouTube.");
            map.put("update_youtube_app_action", "Mise \u00e0 jour appli YouTube");
        } else if ("hi".equals(str)) {
            map.put("error_initializing_player", "YouTube \u092a\u094d\u0932\u0947\u092f\u0930 \u0915\u094b \u092a\u094d\u0930\u093e\u0930\u0902\u092d \u0915\u0930\u0924\u0947 \u0938\u092e\u092f \u0915\u094b\u0908 \u0924\u094d\u0930\u0941\u091f\u093f \u0906\u0908.");
            map.put("get_youtube_app_title", "YouTube \u090f\u092a\u094d\u0932\u093f. \u092a\u094d\u0930\u093e\u092a\u094d\u0924 \u0915\u0930\u0947\u0902");
            map.put("get_youtube_app_text", "\u092f\u0939 \u090f\u092a\u094d\u0932\u093f\u0915\u0947\u0936\u0928 YouTube \u090f\u092a\u094d\u0932\u093f\u0915\u0947\u0936\u0928 \u0915\u0947 \u092c\u093f\u0928\u093e \u0928\u0939\u0940\u0902 \u091a\u0932\u0947\u0917\u093e, \u091c\u094b \u0906\u092a\u0915\u0947 \u0909\u092a\u0915\u0930\u0923 \u092a\u0930 \u092e\u094c\u091c\u0942\u0926 \u0928\u0939\u0940\u0902 \u0939\u0948");
            map.put("get_youtube_app_action", "YouTube \u090f\u092a\u094d\u0932\u093f. \u092a\u094d\u0930\u093e\u092a\u094d\u0924 \u0915\u0930\u0947\u0902");
            map.put("enable_youtube_app_title", "YouTube \u090f\u092a\u094d\u0932\u093f. \u0938\u0915\u094d\u0937\u092e \u0915\u0930\u0947\u0902");
            map.put("enable_youtube_app_text", "\u091c\u092c \u0924\u0915 \u0906\u092a YouTube \u090f\u092a\u094d\u0932\u093f\u0915\u0947\u0936\u0928 \u0938\u0915\u094d\u0937\u092e \u0928\u0939\u0940\u0902 \u0915\u0930\u0924\u0947, \u0924\u092c \u0924\u0915 \u092f\u0939 \u090f\u092a\u094d\u0932\u093f\u0915\u0947\u0936\u0928 \u0915\u093e\u0930\u094d\u092f \u0928\u0939\u0940\u0902 \u0915\u0930\u0947\u0917\u093e.");
            map.put("enable_youtube_app_action", "YouTube \u090f\u092a\u094d\u0932\u093f. \u0938\u0915\u094d\u0937\u092e \u0915\u0930\u0947\u0902");
            map.put("update_youtube_app_title", "YouTube \u090f\u092a\u094d\u0932\u093f. \u0905\u092a\u0921\u0947\u091f \u0915\u0930\u0947\u0902");
            map.put("update_youtube_app_text", "\u091c\u092c \u0924\u0915 \u0906\u092a YouTube \u090f\u092a\u094d\u0932\u093f\u0915\u0947\u0936\u0928 \u0905\u092a\u0921\u0947\u091f \u0928\u0939\u0940\u0902 \u0915\u0930\u0924\u0947, \u0924\u092c \u0924\u0915 \u092f\u0939 \u090f\u092a\u094d\u0932\u093f\u0915\u0947\u0936\u0928 \u0915\u093e\u0930\u094d\u092f \u0928\u0939\u0940\u0902 \u0915\u0930\u0947\u0917\u093e.");
            map.put("update_youtube_app_action", "YouTube \u090f\u092a\u094d\u0932\u093f. \u0905\u092a\u0921\u0947\u091f \u0915\u0930\u0947\u0902");
        } else if ("hr".equals(str)) {
            map.put("error_initializing_player", "Dogodila se pogre\u0161ka tijekom pokretanja playera usluge YouTube.");
            map.put("get_youtube_app_title", "Preuzimanje apl. YouTube");
            map.put("get_youtube_app_text", "Ova se aplikacija ne mo\u017ee pokrenuti bez aplikacije YouTube, koja nije instalirana na va\u0161 ure\u0111aj");
            map.put("get_youtube_app_action", "Preuzmi apl. YouTube");
            map.put("enable_youtube_app_title", "Omogu\u0107avanje apl. YouTube");
            map.put("enable_youtube_app_text", "Ova aplikacija ne\u0107e funkcionirati ako ne omogu\u0107ite aplikaciju YouTube.");
            map.put("enable_youtube_app_action", "Omogu\u0107i apl. YouTube");
            map.put("update_youtube_app_title", "A\u017euriranje apl. YouTube");
            map.put("update_youtube_app_text", "Ova aplikacija ne\u0107e funkcionirati ako ne a\u017eurirate aplikaciju YouTube.");
            map.put("update_youtube_app_action", "A\u017euriraj apl. YouTube");
        } else if ("hu".equals(str)) {
            map.put("error_initializing_player", "Hiba t\u00f6rt\u00e9nt a YouTube lej\u00e1tsz\u00f3 inicializ\u00e1l\u00e1sa sor\u00e1n.");
            map.put("get_youtube_app_title", "YouTube alk. let\u00f6lt\u00e9se");
            map.put("get_youtube_app_text", "Ez az alkalmaz\u00e1s nem fut a YouTube alkalmaz\u00e1s n\u00e9lk\u00fcl, amely hi\u00e1nyzik az eszk\u00f6z\u00e9r\u0151l.");
            map.put("get_youtube_app_action", "YouTube alk. let\u00f6lt\u00e9se");
            map.put("enable_youtube_app_title", "YouTube alkalmaz\u00e1s enged.");
            map.put("enable_youtube_app_text", "Az alkalmaz\u00e1s csak akkor fog m\u0171k\u00f6dni, ha enged\u00e9lyezi a YouTube alkalmaz\u00e1st.");
            map.put("enable_youtube_app_action", "YouTube alkalmaz\u00e1s enged.");
            map.put("update_youtube_app_title", "YouTube alk. friss\u00edt\u00e9se");
            map.put("update_youtube_app_text", "Az alkalmaz\u00e1s csak akkor fog m\u0171k\u00f6dni, ha friss\u00edti a YouTube alkalmaz\u00e1st.");
            map.put("update_youtube_app_action", "YouTube alk. friss\u00edt\u00e9se");
        } else if ("in".equals(str)) {
            map.put("error_initializing_player", "Terjadi kesalahan saat memulai pemutar YouTube.");
            map.put("get_youtube_app_title", "Dapatkan Aplikasi YouTube");
            map.put("get_youtube_app_text", "Aplikasi ini tidak akan berjalan tanpa Aplikasi YouTube, yang hilang dari perangkat Anda");
            map.put("get_youtube_app_action", "Dapatkan Aplikasi YouTube");
            map.put("enable_youtube_app_title", "Aktifkan Aplikasi YouTube");
            map.put("enable_youtube_app_text", "Aplikasi ini tidak akan bekerja kecuali Anda mengaktifkan Aplikasi YouTube.");
            map.put("enable_youtube_app_action", "Aktifkan Aplikasi YouTube");
            map.put("update_youtube_app_title", "Perbarui Aplikasi YouTube");
            map.put("update_youtube_app_text", "Aplikasi ini tidak akan bekerja kecuali Anda memperbarui Aplikasi YouTube.");
            map.put("update_youtube_app_action", "Perbarui Aplikasi YouTube");
        } else if ("it".equals(str)) {
            map.put("error_initializing_player", "Si \u00e8 verificato un errore durante l'inizializzazione del player di YouTube.");
            map.put("get_youtube_app_title", "Scarica app YouTube");
            map.put("get_youtube_app_text", "Questa applicazione non funzioner\u00e0 senza l'applicazione YouTube che non \u00e8 presente sul tuo dispositivo");
            map.put("get_youtube_app_action", "Scarica app YouTube");
            map.put("enable_youtube_app_title", "Attiva app YouTube");
            map.put("enable_youtube_app_text", "Questa applicazione non funzioner\u00e0 se non attivi l'applicazione YouTube.");
            map.put("enable_youtube_app_action", "Attiva app YouTube");
            map.put("update_youtube_app_title", "Aggiorna app YouTube");
            map.put("update_youtube_app_text", "Questa applicazione non funzioner\u00e0 se non aggiorni l'applicazione YouTube.");
            map.put("update_youtube_app_action", "Aggiorna app YouTube");
        } else if ("iw".equals(str)) {
            map.put("error_initializing_player", "\u05d0\u05d9\u05e8\u05e2\u05d4 \u05e9\u05d2\u05d9\u05d0\u05d4 \u05d1\u05e2\u05ea \u05d0\u05ea\u05d7\u05d5\u05dc \u05e0\u05d2\u05df YouTube.");
            map.put("get_youtube_app_title", "\u05e7\u05d1\u05dc \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube");
            map.put("get_youtube_app_text", "\u05d9\u05d9\u05e9\u05d5\u05dd \u05d6\u05d4 \u05dc\u05d0 \u05d9\u05e4\u05e2\u05dc \u05dc\u05dc\u05d0 \u05d9\u05d9\u05e9\u05d5\u05dd YouTube, \u05e9\u05d0\u05d9\u05e0\u05d5 \u05de\u05d5\u05ea\u05e7\u05df \u05d1\u05de\u05db\u05e9\u05d9\u05e8 \u05e9\u05dc\u05da");
            map.put("get_youtube_app_action", "\u05e7\u05d1\u05dc \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube");
            map.put("enable_youtube_app_title", "\u05d4\u05e4\u05e2\u05dc \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube");
            map.put("enable_youtube_app_text", "\u05d9\u05d9\u05e9\u05d5\u05dd \u05d6\u05d4 \u05dc\u05d0 \u05d9\u05e2\u05d1\u05d5\u05d3 \u05d0\u05dc\u05d0 \u05d0\u05dd \u05ea\u05e4\u05e2\u05d9\u05dc \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube.");
            map.put("enable_youtube_app_action", "\u05d4\u05e4\u05e2\u05dc \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube");
            map.put("update_youtube_app_title", "\u05e2\u05d3\u05db\u05df \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube");
            map.put("update_youtube_app_text", "\u05d9\u05d9\u05e9\u05d5\u05dd \u05d6\u05d4 \u05dc\u05d0 \u05d9\u05e2\u05d1\u05d5\u05d3 \u05d0\u05dc\u05d0 \u05d0\u05dd \u05ea\u05e2\u05d3\u05db\u05df \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube.");
            map.put("update_youtube_app_action", "\u05e2\u05d3\u05db\u05df \u05d0\u05ea \u05d9\u05d9\u05e9\u05d5\u05dd YouTube");
        } else if ("ja".equals(str)) {
            map.put("error_initializing_player", "YouTube\u30d7\u30ec\u30fc\u30e4\u30fc\u306e\u521d\u671f\u5316\u4e2d\u306b\u30a8\u30e9\u30fc\u304c\u767a\u751f\u3057\u307e\u3057\u305f\u3002");
            map.put("get_youtube_app_title", "YouTube\u30a2\u30d7\u30ea\u3092\u5165\u624b");
            map.put("get_youtube_app_text", "\u3053\u306e\u30a2\u30d7\u30ea\u306e\u5b9f\u884c\u306b\u5fc5\u8981\u306aYouTube\u30a2\u30d7\u30ea\u304c\u7aef\u672b\u306b\u3042\u308a\u307e\u305b\u3093");
            map.put("get_youtube_app_action", "YouTube\u30a2\u30d7\u30ea\u3092\u5165\u624b");
            map.put("enable_youtube_app_title", "YouTube\u30a2\u30d7\u30ea\u3092\u6709\u52b9\u5316");
            map.put("enable_youtube_app_text", "\u3053\u306e\u30a2\u30d7\u30ea\u306e\u5b9f\u884c\u306b\u306fYouTube\u30a2\u30d7\u30ea\u306e\u6709\u52b9\u5316\u304c\u5fc5\u8981\u3067\u3059\u3002");
            map.put("enable_youtube_app_action", "YouTube\u30a2\u30d7\u30ea\u3092\u6709\u52b9\u5316");
            map.put("update_youtube_app_title", "YouTube\u30a2\u30d7\u30ea\u3092\u66f4\u65b0");
            map.put("update_youtube_app_text", "\u3053\u306e\u30a2\u30d7\u30ea\u306e\u5b9f\u884c\u306b\u306fYouTube\u30a2\u30d7\u30ea\u306e\u66f4\u65b0\u304c\u5fc5\u8981\u3067\u3059\u3002");
            map.put("update_youtube_app_action", "YouTube\u30a2\u30d7\u30ea\u3092\u66f4\u65b0");
        } else if ("ko".equals(str)) {
            map.put("error_initializing_player", "YouTube \ud50c\ub808\uc774\uc5b4\ub97c \ucd08\uae30\ud654\ud558\ub294 \uc911\uc5d0 \uc624\ub958\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4.");
            map.put("get_youtube_app_title", "YouTube \uc571 \ub2e4\uc6b4\ub85c\ub4dc");
            map.put("get_youtube_app_text", "\uc774 \uc571\uc740 \ub0b4 \uae30\uae30\uc5d0 YouTube \uc571\uc774 \uc5c6\uc5b4\uc11c \uc2e4\ud589\ub418\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.");
            map.put("get_youtube_app_action", "YouTube \uc571 \ub2e4\uc6b4\ub85c\ub4dc");
            map.put("enable_youtube_app_title", "YouTube \uc571 \uc0ac\uc6a9 \uc124\uc815");
            map.put("enable_youtube_app_text", "\uc774 \uc571\uc740 YouTube \uc571\uc744 \uc0ac\uc6a9\ud558\ub3c4\ub85d \uc124\uc815\ud558\uc9c0 \uc54a\uc73c\uba74 \uc791\ub3d9\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.");
            map.put("enable_youtube_app_action", "YouTube \uc571 \uc0ac\uc6a9");
            map.put("update_youtube_app_title", "YouTube \uc571 \uc5c5\ub370\uc774\ud2b8");
            map.put("update_youtube_app_text", "\uc774 \uc571\uc740 YouTube \uc571\uc744 \uc5c5\ub370\uc774\ud2b8\ud558\uc9c0 \uc54a\uc73c\uba74 \uc791\ub3d9\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.");
            map.put("update_youtube_app_action", "YouTube \uc571 \uc5c5\ub370\uc774\ud2b8");
        } else if ("lt".equals(str)) {
            map.put("error_initializing_player", "Inicijuojant \u201eYouTube\u201c grotuv\u0105 \u012fvyko klaida.");
            map.put("get_youtube_app_title", "Gauti \u201eYouTube\u201c program\u0105");
            map.put("get_youtube_app_text", "\u0160i programa neveikia be \u201eYouTube\u201c programos, o jos \u012frenginyje n\u0117ra.");
            map.put("get_youtube_app_action", "Gauti \u201eYouTube\u201c program\u0105");
            map.put("enable_youtube_app_title", "\u012egalinti \u201eYouTube\u201c progr.");
            map.put("enable_youtube_app_text", "\u0160i programa neveiks, jei ne\u012fgalinsite \u201eYouTube\u201c programos.");
            map.put("enable_youtube_app_action", "\u012egalinti \u201eYouTube\u201c progr.");
            map.put("update_youtube_app_title", "Atnauj. \u201eYouTube\u201c progr.");
            map.put("update_youtube_app_text", "\u0160i programa neveiks, jei neatnaujinsite \u201eYouTube\u201c programos.");
            map.put("update_youtube_app_action", "Atnauj. \u201eYouTube\u201c progr.");
        } else if ("lv".equals(str)) {
            map.put("error_initializing_player", "Inicializ\u0113jot YouTube atska\u0146ot\u0101ju, rad\u0101s k\u013c\u016bda.");
            map.put("get_youtube_app_title", "YouTube liet. ieg\u016b\u0161ana");
            map.put("get_youtube_app_text", "\u0160\u012b lietotne nedarbosies bez YouTube lietotnes, kuras nav \u0161aj\u0101 ier\u012bc\u0113.");
            map.put("get_youtube_app_action", "Ieg\u016bt YouTube lietotni");
            map.put("enable_youtube_app_title", "YouTube liet. iesp\u0113jo\u0161ana");
            map.put("enable_youtube_app_text", "Lai \u0161\u012b lietotne darbotos, iesp\u0113jojiet YouTube lietotni.");
            map.put("enable_youtube_app_action", "Iesp\u0113jot YouTube lietotni");
            map.put("update_youtube_app_title", "YouTube liet. atjaunin.");
            map.put("update_youtube_app_text", "Lai \u0161\u012b lietotne darbotos, atjauniniet YouTube lietotni.");
            map.put("update_youtube_app_action", "Atjaun. YouTube lietotni");
        } else if ("ms".equals(str)) {
            map.put("error_initializing_player", "Ralat berlaku semasa memulakan alat main YouTube.");
            map.put("get_youtube_app_title", "Dapatkan Apl YouTube");
            map.put("get_youtube_app_text", "Apl ini tidak akan berjalan tanpa Apl YouTube, yang tidak ada pada peranti anda");
            map.put("get_youtube_app_action", "Dapatkan Apl YouTube");
            map.put("enable_youtube_app_title", "Dayakan Apl YouTube");
            map.put("enable_youtube_app_text", "Apl ini tidak akan berfungsi kecuali anda mendayakan Apl YouTube.");
            map.put("enable_youtube_app_action", "Dayakan Apl YouTube");
            map.put("update_youtube_app_title", "Kemas kini Apl YouTube");
            map.put("update_youtube_app_text", "Apl ini tidak akan berfungsi kecuali anda mengemas kini Apl YouTube.");
            map.put("update_youtube_app_action", "Kemas kini Apl YouTube");
        } else if ("nb".equals(str)) {
            map.put("error_initializing_player", "Det oppsto en feil da YouTube-avspilleren startet.");
            map.put("get_youtube_app_title", "Skaff deg YouTube-appen");
            map.put("get_youtube_app_text", "Denne appen kan ikke kj\u00f8re uten YouTube-appen, som du ikke har p\u00e5 enheten");
            map.put("get_youtube_app_action", "Skaff deg YouTube-appen");
            map.put("enable_youtube_app_title", "Aktiver YouTube-appen");
            map.put("enable_youtube_app_text", "Denne appen fungerer ikke f\u00f8r du aktiverer YouTube-appen.");
            map.put("enable_youtube_app_action", "Aktiver YouTube-appen");
            map.put("update_youtube_app_title", "Oppdater YouTube-appen");
            map.put("update_youtube_app_text", "Denne appen fungerer ikke f\u00f8r du oppdaterer YouTube-appen.");
            map.put("update_youtube_app_action", "Oppdater YouTube-appen");
        } else if ("nl".equals(str)) {
            map.put("error_initializing_player", "Er is een fout opgetreden bij het initialiseren van de YouTube-speler.");
            map.put("get_youtube_app_title", "YouTube-app downloaden");
            map.put("get_youtube_app_text", "Deze app wordt niet uitgevoerd zonder de YouTube-app, die op uw apparaat ontbreekt");
            map.put("get_youtube_app_action", "YouTube-app downloaden");
            map.put("enable_youtube_app_title", "YouTube-app inschakelen");
            map.put("enable_youtube_app_text", "Deze app werkt niet, tenzij u de YouTube-app inschakelt.");
            map.put("enable_youtube_app_action", "YouTube-app inschakelen");
            map.put("update_youtube_app_title", "YouTube-app bijwerken");
            map.put("update_youtube_app_text", "Deze app werkt niet, tenzij u de YouTube-app bijwerkt.");
            map.put("update_youtube_app_action", "YouTube-app bijwerken");
        } else if ("pl".equals(str)) {
            map.put("error_initializing_player", "Podczas inicjowania odtwarzacza YouTube wyst\u0105pi\u0142 b\u0142\u0105d.");
            map.put("get_youtube_app_title", "Pobierz aplikacj\u0119 YouTube");
            map.put("get_youtube_app_text", "Ta aplikacja nie b\u0119dzie dzia\u0142a\u0107 bez aplikacji YouTube, kt\u00f3rej nie ma na tym urz\u0105dzeniu");
            map.put("get_youtube_app_action", "Pobierz aplikacj\u0119 YouTube");
            map.put("enable_youtube_app_title", "W\u0142\u0105cz aplikacj\u0119 YouTube");
            map.put("enable_youtube_app_text", "Ta aplikacja nie b\u0119dzie dzia\u0142a\u0107, je\u015bli nie w\u0142\u0105czysz aplikacji YouTube.");
            map.put("enable_youtube_app_action", "W\u0142\u0105cz aplikacj\u0119 YouTube");
            map.put("update_youtube_app_title", "Zaktualizuj aplikacj\u0119 YouTube");
            map.put("update_youtube_app_text", "Ta aplikacja nie b\u0119dzie dzia\u0142a\u0107, je\u015bli nie zaktualizujesz aplikacji YouTube.");
            map.put("update_youtube_app_action", "Zaktualizuj aplikacj\u0119 YouTube");
        } else if ("pt_PT".equals(str)) {
            map.put("error_initializing_player", "Ocorreu um erro ao iniciar o leitor do YouTube.");
            map.put("get_youtube_app_title", "Obter a Aplica\u00e7\u00e3o YouTube");
            map.put("get_youtube_app_text", "Esta aplica\u00e7\u00e3o n\u00e3o ser\u00e1 executada sem a Aplica\u00e7\u00e3o YouTube, que est\u00e1 em falta no seu dispositivo");
            map.put("get_youtube_app_action", "Obter a Aplica\u00e7\u00e3o YouTube");
            map.put("enable_youtube_app_title", "Ativar Aplica\u00e7\u00e3o YouTube");
            map.put("enable_youtube_app_text", "Esta aplica\u00e7\u00e3o n\u00e3o ir\u00e1 funcionar enquanto n\u00e3o ativar a Aplica\u00e7\u00e3o YouTube.");
            map.put("enable_youtube_app_action", "Ativar Aplica\u00e7\u00e3o YouTube");
            map.put("update_youtube_app_title", "Atualizar Aplica. YouTube");
            map.put("update_youtube_app_text", "Esta aplica\u00e7\u00e3o n\u00e3o ir\u00e1 funcionar enquanto n\u00e3o atualizar a Aplica\u00e7\u00e3o YouTube.");
            map.put("update_youtube_app_action", "Atualizar Aplica. YouTube");
        } else if ("pt".equals(str)) {
            map.put("error_initializing_player", "Ocorreu um erro ao inicializar o player do YouTube.");
            map.put("get_youtube_app_title", "Obter aplicativo YouTube");
            map.put("get_youtube_app_text", "Este aplicativo s\u00f3 funciona com o aplicativo YouTube, que est\u00e1 ausente no dispositivo.");
            map.put("get_youtube_app_action", "Obter aplicativo YouTube");
            map.put("enable_youtube_app_title", "Ativar aplicativo YouTube");
            map.put("enable_youtube_app_text", "Este aplicativo s\u00f3 funciona com o aplicativo YouTube ativado.");
            map.put("enable_youtube_app_action", "Ativar aplicativo YouTube");
            map.put("update_youtube_app_title", "Atualizar aplic. YouTube");
            map.put("update_youtube_app_text", "Este aplicativo s\u00f3 funciona com o aplicativo YouTube atualizado.");
            map.put("update_youtube_app_action", "Atualizar aplic. YouTube");
        } else if ("ro".equals(str)) {
            map.put("error_initializing_player", "A ap\u0103rut o eroare la ini\u0163ializarea playerului YouTube.");
            map.put("get_youtube_app_title", "Desc\u0103rca\u0163i YouTube");
            map.put("get_youtube_app_text", "Aceast\u0103 aplica\u0163ie nu va rula f\u0103r\u0103 aplica\u0163ia YouTube, care lipse\u015fte de pe gadget");
            map.put("get_youtube_app_action", "Desc\u0103rca\u0163i YouTube");
            map.put("enable_youtube_app_title", "Activa\u0163i YouTube");
            map.put("enable_youtube_app_text", "Aceast\u0103 aplica\u0163ie nu va func\u0163iona dec\u00e2t dac\u0103 activa\u0163i aplica\u0163ia YouTube.");
            map.put("enable_youtube_app_action", "Activa\u0163i YouTube");
            map.put("update_youtube_app_title", "Actualiza\u0163i YouTube");
            map.put("update_youtube_app_text", "Aceast\u0103 aplica\u0163ie nu va func\u0163iona dec\u00e2t dac\u0103 actualiza\u0163i aplica\u0163ia YouTube.");
            map.put("update_youtube_app_action", "Actualiza\u0163i YouTube");
        } else if ("ru".equals(str)) {
            map.put("error_initializing_player", "\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c \u043f\u0440\u043e\u0438\u0433\u0440\u044b\u0432\u0430\u0442\u0435\u043b\u044c YouTube.");
            map.put("get_youtube_app_title", "\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u0435 YouTube");
            map.put("get_youtube_app_text", "\u0427\u0442\u043e\u0431\u044b \u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c \u044d\u0442\u0443 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u0443, \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u0435 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 YouTube.");
            map.put("get_youtube_app_action", "\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c YouTube");
            map.put("enable_youtube_app_title", "\u0410\u043a\u0442\u0438\u0432\u0430\u0446\u0438\u044f YouTube");
            map.put("enable_youtube_app_text", "\u0427\u0442\u043e\u0431\u044b \u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c \u044d\u0442\u0443 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u0443, \u0430\u043a\u0442\u0438\u0432\u0438\u0440\u0443\u0439\u0442\u0435 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 YouTube.");
            map.put("enable_youtube_app_action", "\u0410\u043a\u0442\u0438\u0432\u0438\u0440\u043e\u0432\u0430\u0442\u044c YouTube");
            map.put("update_youtube_app_title", "\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u0435 YouTube");
            map.put("update_youtube_app_text", "\u0427\u0442\u043e\u0431\u044b \u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c \u044d\u0442\u0443 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u043c\u0443, \u043e\u0431\u043d\u043e\u0432\u0438\u0442\u0435 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 YouTube.");
            map.put("update_youtube_app_action", "\u041e\u0431\u043d\u043e\u0432\u0438\u0442\u044c YouTube");
        } else if ("sk".equals(str)) {
            map.put("error_initializing_player", "Pri inicializ\u00e1cii prehr\u00e1va\u010da YouTube sa vyskytla chyba.");
            map.put("get_youtube_app_title", "Z\u00edska\u0165 aplik\u00e1ciu YouTube");
            map.put("get_youtube_app_text", "T\u00fato aplik\u00e1ciu nebude mo\u017en\u00e9 spusti\u0165 bez aplik\u00e1cie YouTube, ktor\u00e1 na zariaden\u00ed nie je nain\u0161talovan\u00e1.");
            map.put("get_youtube_app_action", "Z\u00edska\u0165 aplik\u00e1ciu YouTube");
            map.put("enable_youtube_app_title", "Povoli\u0165 aplik\u00e1ciu YouTube");
            map.put("enable_youtube_app_text", "T\u00e1to aplik\u00e1cia bude fungova\u0165 a\u017e po povolen\u00ed aplik\u00e1cie YouTube.");
            map.put("enable_youtube_app_action", "Povoli\u0165 aplik\u00e1ciu YouTube");
            map.put("update_youtube_app_title", "Aktualizova\u0165 apl. YouTube");
            map.put("update_youtube_app_text", "T\u00e1to aplik\u00e1cia bude fungova\u0165 a\u017e po aktualiz\u00e1cii aplik\u00e1cie YouTube.");
            map.put("update_youtube_app_action", "Aktualizova\u0165 apl. YouTube");
        } else if ("sl".equals(str)) {
            map.put("error_initializing_player", "Napaka med inicializacijo YouTubovega predvajalnika.");
            map.put("get_youtube_app_title", "Prenos aplikacije YouTube");
            map.put("get_youtube_app_text", "Ta aplikacija ne bo delovala brez aplikacije YouTube, ki je ni v va\u0161i napravi");
            map.put("get_youtube_app_action", "Prenos aplikacije YouTube");
            map.put("enable_youtube_app_title", "Omog. aplikacije YouTube");
            map.put("enable_youtube_app_text", "Ta aplikacija ne bo delovala, \u010de ne omogo\u010dite aplikacije YouTube.");
            map.put("enable_youtube_app_action", "Omog. aplikacijo YouTube");
            map.put("update_youtube_app_title", "Posodob. aplikacije YouTube");
            map.put("update_youtube_app_text", "Ta aplikacija ne bo delovala, \u010de ne posodobite aplikacije YouTube.");
            map.put("update_youtube_app_action", "Posod. aplikacijo YouTube");
        } else if ("sr".equals(str)) {
            map.put("error_initializing_player", "\u0414\u043e\u0448\u043b\u043e \u0458\u0435 \u0434\u043e \u0433\u0440\u0435\u0448\u043a\u0435 \u043f\u0440\u0438 \u043f\u043e\u043a\u0440\u0435\u0442\u0430\u045a\u0443 YouTube \u043f\u043b\u0435\u0458\u0435\u0440\u0430.");
            map.put("get_youtube_app_title", "\u041f\u0440\u0435\u0443\u0437\u0438\u043c\u0430\u045a\u0435 \u0430\u043f\u043b\u0438\u043a. YouTube");
            map.put("get_youtube_app_text", "\u041e\u0432\u0430 \u0430\u043f\u043b\u0438\u043a\u0430\u0446\u0438\u0458\u0430 \u043d\u0435\u045b\u0435 \u0444\u0443\u043d\u043a\u0446\u0438\u043e\u043d\u0438\u0441\u0430\u0442\u0438 \u0431\u0435\u0437 \u0430\u043f\u043b\u0438\u043a\u0430\u0446\u0438\u0458\u0435 YouTube, \u043a\u043e\u0458\u0430 \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0458\u0435 \u043d\u0430 \u0443\u0440\u0435\u0452\u0430\u0458\u0443");
            map.put("get_youtube_app_action", "\u041f\u0440\u0435\u0443\u0437\u043c\u0438 \u0430\u043f\u043b\u0438\u043a\u0430\u0446. YouTube");
            map.put("enable_youtube_app_title", "\u041e\u043c\u043e\u0433\u0443\u045b\u0430\u0432\u0430\u045a\u0435 \u0430\u043f\u043b. YouTube");
            map.put("enable_youtube_app_text", "\u041e\u0432\u0430 \u0430\u043f\u043b\u0438\u043a\u0430\u0446\u0438\u0458\u0435 \u043d\u0435\u045b\u0435 \u0444\u0443\u043d\u043a\u0446\u0438\u043e\u043d\u0438\u0441\u0430\u0442\u0438 \u0430\u043a\u043e \u043d\u0435 \u043e\u043c\u043e\u0433\u0443\u045b\u0438\u0442\u0435 \u0430\u043f\u043b\u0438\u043a\u0430\u0446\u0438\u0458\u0443 YouTube.");
            map.put("enable_youtube_app_action", "\u041e\u043c\u043e\u0433\u0443\u045b\u0438 \u0430\u043f\u043b\u0438\u043a\u0430\u0446. YouTube");
            map.put("update_youtube_app_title", "\u0410\u0436\u0443\u0440\u0438\u0440\u0430\u045a\u0435 \u0430\u043f\u043b\u0438\u043a. YouTube");
            map.put("update_youtube_app_text", "\u041e\u0432\u0430 \u0430\u043f\u043b\u0438\u043a\u0430\u0446\u0438\u0458\u0435 \u043d\u0435\u045b\u0435 \u0444\u0443\u043d\u043a\u0446\u0438\u043e\u043d\u0438\u0441\u0430\u0442\u0438 \u0430\u043a\u043e \u043d\u0435 \u0430\u0436\u0443\u0440\u0438\u0440\u0430\u0442\u0435 \u0430\u043f\u043b\u0438\u043a\u0430\u0446\u0438\u0458\u0443 YouTube.");
            map.put("update_youtube_app_action", "\u0410\u0436\u0443\u0440\u0438\u0440\u0430\u0458 \u0430\u043f\u043b\u0438\u043a\u0430\u0446. YouTube");
        } else if ("sv".equals(str)) {
            map.put("error_initializing_player", "Ett fel uppstod n\u00e4r YouTube-spelaren skulle startas.");
            map.put("get_youtube_app_title", "H\u00e4mta YouTube-appen");
            map.put("get_youtube_app_text", "YouTube-appen kr\u00e4vs f\u00f6r att den h\u00e4r appen ska kunna k\u00f6ras. Du har inte YouTube-appen p\u00e5 din enhet.");
            map.put("get_youtube_app_action", "H\u00e4mta YouTube-appen");
            map.put("enable_youtube_app_title", "Aktivera YouTube-appen");
            map.put("enable_youtube_app_text", "Du m\u00e5ste aktivera YouTube-appen f\u00f6r att den h\u00e4r appen ska fungera.");
            map.put("enable_youtube_app_action", "Aktivera YouTube-appen");
            map.put("update_youtube_app_title", "Uppdatera YouTube-appen");
            map.put("update_youtube_app_text", "Du m\u00e5ste uppdatera YouTube-appen f\u00f6r att den h\u00e4r appen ska fungera.");
            map.put("update_youtube_app_action", "Uppdatera YouTube-appen");
        } else if ("sw".equals(str)) {
            map.put("error_initializing_player", "Hitilafu ilitokea wakati wa kuanzisha kichezeshi cha YouTube.");
            map.put("get_youtube_app_title", "Pata Programu ya YouTube");
            map.put("get_youtube_app_text", "Programu hii haitaendeshwa bila Programu ya YouTube, ambayo inakosekana kwenye kifaa chako.");
            map.put("get_youtube_app_action", "Pata Programu ya YouTube");
            map.put("enable_youtube_app_title", "Wezesha Programu ya YouTube");
            map.put("enable_youtube_app_text", "Programu hii haitafanya kazi isipokuwa uwezeshe Programu ya YouTube.");
            map.put("enable_youtube_app_action", "Wezesha Programu ya YouTube");
            map.put("update_youtube_app_title", "Sasisha Programu ya YouTube");
            map.put("update_youtube_app_text", "Programu hii haitafanya kazi mpaka usasishe Programu ya YouTube.");
            map.put("update_youtube_app_action", "Sasisha Programu ya YouTube");
        } else if ("th".equals(str)) {
            map.put("error_initializing_player", "\u0e40\u0e01\u0e34\u0e14\u0e02\u0e49\u0e2d\u0e1c\u0e34\u0e14\u0e1e\u0e25\u0e32\u0e14\u0e43\u0e19\u0e02\u0e13\u0e30\u0e40\u0e23\u0e34\u0e48\u0e21\u0e15\u0e49\u0e19\u0e42\u0e1b\u0e23\u0e41\u0e01\u0e23\u0e21\u0e40\u0e25\u0e48\u0e19 YouTube");
            map.put("get_youtube_app_title", "\u0e23\u0e31\u0e1a\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19 YouTube");
            map.put("get_youtube_app_text", "\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19\u0e19\u0e35\u0e49\u0e08\u0e30\u0e44\u0e21\u0e48\u0e17\u0e33\u0e07\u0e32\u0e19\u0e2b\u0e32\u0e01\u0e44\u0e21\u0e48\u0e21\u0e35\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19 YouTube \u0e0b\u0e36\u0e48\u0e07\u0e44\u0e21\u0e48\u0e21\u0e35\u0e43\u0e19\u0e2d\u0e38\u0e1b\u0e01\u0e23\u0e13\u0e4c\u0e02\u0e2d\u0e07\u0e04\u0e38\u0e13");
            map.put("get_youtube_app_action", "\u0e23\u0e31\u0e1a\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19 YouTube");
            map.put("enable_youtube_app_title", "\u0e40\u0e1b\u0e34\u0e14\u0e43\u0e0a\u0e49\u0e07\u0e32\u0e19\u0e41\u0e2d\u0e1b YouTube");
            map.put("enable_youtube_app_text", "\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19\u0e19\u0e35\u0e49\u0e08\u0e30\u0e44\u0e21\u0e48\u0e17\u0e33\u0e07\u0e32\u0e19\u0e08\u0e19\u0e01\u0e27\u0e48\u0e32\u0e04\u0e38\u0e13\u0e08\u0e30\u0e40\u0e1b\u0e34\u0e14\u0e43\u0e0a\u0e49\u0e07\u0e32\u0e19\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19 YouTube");
            map.put("enable_youtube_app_action", "\u0e40\u0e1b\u0e34\u0e14\u0e43\u0e0a\u0e49\u0e07\u0e32\u0e19\u0e41\u0e2d\u0e1b YouTube");
            map.put("update_youtube_app_title", "\u0e2d\u0e31\u0e1b\u0e40\u0e14\u0e15\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19 YouTube");
            map.put("update_youtube_app_text", "\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19\u0e19\u0e35\u0e49\u0e08\u0e30\u0e44\u0e21\u0e48\u0e17\u0e33\u0e07\u0e32\u0e19\u0e08\u0e19\u0e01\u0e27\u0e48\u0e32\u0e04\u0e38\u0e13\u0e08\u0e30\u0e2d\u0e31\u0e1b\u0e40\u0e14\u0e15\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19 YouTube");
            map.put("update_youtube_app_action", "\u0e2d\u0e31\u0e1b\u0e40\u0e14\u0e15\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19 YouTube");
        } else if ("tl".equals(str)) {
            map.put("error_initializing_player", "May naganap na error habang sinisimulan ang player ng YouTube.");
            map.put("get_youtube_app_title", "Kunin ang YouTube App");
            map.put("get_youtube_app_text", "Hindi gagana ang app na ito nang wala ang YouTube App, na nawawala sa iyong device");
            map.put("get_youtube_app_action", "Kunin ang YouTube App");
            map.put("enable_youtube_app_title", "Paganahin ang YouTube App");
            map.put("enable_youtube_app_text", "Hindi gagana ang app na ito maliban kung paganahin mo ang YouTube App.");
            map.put("enable_youtube_app_action", "Paganahin ang YouTube App");
            map.put("update_youtube_app_title", "I-update ang YouTube App");
            map.put("update_youtube_app_text", "Hindi gagana ang app na ito maliban kung i-update mo ang YouTube App.");
            map.put("update_youtube_app_action", "I-update ang YouTube App");
        } else if ("tr".equals(str)) {
            map.put("error_initializing_player", "YouTube oynat\u0131c\u0131s\u0131 ba\u015flat\u0131l\u0131rken bir hata olu\u015ftu.");
            map.put("get_youtube_app_title", "YouTube Uygulamas\u0131n\u0131 edinin");
            map.put("get_youtube_app_text", "Cihaz\u0131n\u0131zda bulunmayan YouTube Uygulamas\u0131 olmadan bu uygulama \u00e7al\u0131\u015fmaz");
            map.put("get_youtube_app_action", "YouTube Uygulamas\u0131n\u0131 edinin");
            map.put("enable_youtube_app_title", "YouTube Uygulamas\u0131n\u0131 etkinle\u015ftirin");
            map.put("enable_youtube_app_text", "YouTube Uygulamas\u0131n\u0131 etkinle\u015ftirmedi\u011finiz s\u00fcrece bu uygulama \u00e7al\u0131\u015fmaz.");
            map.put("enable_youtube_app_action", "YouTube Uygulamas\u0131n\u0131 etkinle\u015ftirin");
            map.put("update_youtube_app_title", "YouTube Uygulamas\u0131n\u0131 g\u00fcncelleyin");
            map.put("update_youtube_app_text", "YouTube Uygulamas\u0131 g\u00fcncellenmedik\u00e7e bu uygulama \u00e7al\u0131\u015fmaz.");
            map.put("update_youtube_app_action", "YouTube Uygulamas\u0131n\u0131 g\u00fcncelle");
        } else if ("uk".equals(str)) {
            map.put("error_initializing_player", "\u041f\u0456\u0434 \u0447\u0430\u0441 \u0456\u043d\u0456\u0446\u0456\u0430\u043b\u0456\u0437\u0430\u0446\u0456\u0457 \u043f\u0440\u043e\u0433\u0440\u0430\u0432\u0430\u0447\u0430 YouTube \u0441\u0442\u0430\u043b\u0430\u0441\u044f \u043f\u043e\u043c\u0438\u043b\u043a\u0430.");
            map.put("get_youtube_app_title", "\u041e\u0442\u0440\u0438\u043c\u0430\u0442\u0438 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube");
            map.put("get_youtube_app_text", "\u0426\u044f \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u043d\u0435 \u0437\u0430\u043f\u0443\u0441\u0442\u0438\u0442\u044c\u0441\u044f \u0431\u0435\u0437 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0438 YouTube, \u044f\u043a\u0443 \u043d\u0435 \u0432\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u043e \u043d\u0430 \u0432\u0430\u0448\u043e\u043c\u0443 \u043f\u0440\u0438\u0441\u0442\u0440\u043e\u0457");
            map.put("get_youtube_app_action", "\u041e\u0442\u0440\u0438\u043c\u0430\u0442\u0438 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube");
            map.put("enable_youtube_app_title", "\u0423\u0432\u0456\u043c\u043a. \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube");
            map.put("enable_youtube_app_text", "\u0426\u044f \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u043d\u0435 \u043f\u0440\u0430\u0446\u044e\u0432\u0430\u0442\u0438\u043c\u0435, \u043f\u043e\u043a\u0438 \u0432\u0438 \u043d\u0435 \u0432\u0432\u0456\u043c\u043a\u043d\u0435\u0442\u0435 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube.");
            map.put("enable_youtube_app_action", "\u0423\u0432\u0456\u043c\u043a. \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube");
            map.put("update_youtube_app_title", "\u041e\u043d\u043e\u0432\u0438\u0442\u0438 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube");
            map.put("update_youtube_app_text", "\u0426\u044f \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0430 \u043d\u0435 \u043f\u0440\u0430\u0446\u044e\u0432\u0430\u0442\u0438\u043c\u0435, \u043f\u043e\u043a\u0438 \u0432\u0438 \u043d\u0435 \u043e\u043d\u043e\u0432\u0438\u0442\u0435 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube.");
            map.put("update_youtube_app_action", "\u041e\u043d\u043e\u0432\u0438\u0442\u0438 \u043f\u0440\u043e\u0433\u0440\u0430\u043c\u0443 YouTube");
        } else if ("vi".equals(str)) {
            map.put("error_initializing_player", "\u0110\u00e3 x\u1ea3y ra l\u1ed7i trong khi kh\u1edfi ch\u1ea1y tr\u00ecnh ph\u00e1t YouTube.");
            map.put("get_youtube_app_title", "T\u1ea3i \u1ee9ng d\u1ee5ng YouTube");
            map.put("get_youtube_app_text", "\u1ee8ng d\u1ee5ng n\u00e0y s\u1ebd kh\u00f4ng ch\u1ea1y n\u1ebfu kh\u00f4ng c\u00f3 \u1ee9ng d\u1ee5ng YouTube, \u1ee9ng d\u1ee5ng n\u00e0y b\u1ecb thi\u1ebfu trong thi\u1ebft b\u1ecb c\u1ee7a b\u1ea1n");
            map.put("get_youtube_app_action", "T\u1ea3i \u1ee9ng d\u1ee5ng YouTube");
            map.put("enable_youtube_app_title", "B\u1eadt \u1ee9ng d\u1ee5ng YouTube");
            map.put("enable_youtube_app_text", "\u1ee8ng d\u1ee5ng n\u00e0y s\u1ebd kh\u00f4ng ho\u1ea1t \u0111\u1ed9ng tr\u1eeb khi b\u1ea1n b\u1eadt \u1ee9ng d\u1ee5ng YouTube.");
            map.put("enable_youtube_app_action", "B\u1eadt \u1ee9ng d\u1ee5ng YouTube");
            map.put("update_youtube_app_title", "C\u1eadp nh\u1eadt \u1ee9ng d\u1ee5ng YouTube");
            map.put("update_youtube_app_text", "\u1ee8ng d\u1ee5ng n\u00e0y s\u1ebd kh\u00f4ng ho\u1ea1t \u0111\u1ed9ng tr\u1eeb khi b\u1ea1n c\u1eadp nh\u1eadt \u1ee9ng d\u1ee5ng YouTube.");
            map.put("update_youtube_app_action", "C\u1eadp nh\u1eadt \u1ee9ng d\u1ee5ng YouTube");
        } else if ("zh_CN".equals(str)) {
            map.put("error_initializing_player", "\u521d\u59cb\u5316 YouTube \u64ad\u653e\u5668\u65f6\u51fa\u73b0\u9519\u8bef\u3002");
            map.put("get_youtube_app_title", "\u83b7\u53d6 YouTube \u5e94\u7528");
            map.put("get_youtube_app_text", "\u60a8\u7684\u8bbe\u5907\u4e2d\u6ca1\u6709 YouTube \u5e94\u7528\uff0c\u60a8\u5fc5\u987b\u5148\u5b89\u88c5 YouTube \u5e94\u7528\u624d\u80fd\u8fd0\u884c\u6b64\u5e94\u7528\u3002");
            map.put("get_youtube_app_action", "\u83b7\u53d6 YouTube \u5e94\u7528");
            map.put("enable_youtube_app_title", "\u542f\u7528 YouTube \u5e94\u7528");
            map.put("enable_youtube_app_text", "\u60a8\u9700\u8981\u542f\u7528 YouTube \u5e94\u7528\u624d\u80fd\u8fd0\u884c\u8be5\u5e94\u7528\u3002");
            map.put("enable_youtube_app_action", "\u542f\u7528 YouTube \u5e94\u7528");
            map.put("update_youtube_app_title", "\u66f4\u65b0 YouTube \u5e94\u7528");
            map.put("update_youtube_app_text", "\u60a8\u5fc5\u987b\u66f4\u65b0 YouTube \u5e94\u7528\u624d\u80fd\u8fd0\u884c\u6b64\u5e94\u7528\u3002");
            map.put("update_youtube_app_action", "\u66f4\u65b0 YouTube \u5e94\u7528");
        } else if ("zh_TW".equals(str)) {
            map.put("error_initializing_player", "\u521d\u59cb\u5316 YouTube \u64ad\u653e\u5668\u6642\u767c\u751f\u932f\u8aa4\u3002");
            map.put("get_youtube_app_title", "\u53d6\u5f97 YouTube \u61c9\u7528\u7a0b\u5f0f");
            map.put("get_youtube_app_text", "\u60a8\u5fc5\u9808\u555f\u7528 YouTube \u61c9\u7528\u7a0b\u5f0f\uff0c\u9019\u500b\u61c9\u7528\u7a0b\u5f0f\u624d\u80fd\u904b\u4f5c\uff0c\u4f46\u7cfb\u7d71\u5728\u88dd\u7f6e\u4e2d\u627e\u4e0d\u5230 YouTube \u61c9\u7528\u7a0b\u5f0f\u3002");
            map.put("get_youtube_app_action", "\u53d6\u5f97 YouTube \u61c9\u7528\u7a0b\u5f0f");
            map.put("enable_youtube_app_title", "\u555f\u7528 YouTube \u61c9\u7528\u7a0b\u5f0f");
            map.put("enable_youtube_app_text", "\u60a8\u5fc5\u9808\u555f\u7528 YouTube \u61c9\u7528\u7a0b\u5f0f\uff0c\u9019\u500b\u61c9\u7528\u7a0b\u5f0f\u624d\u80fd\u904b\u4f5c\u3002");
            map.put("enable_youtube_app_action", "\u555f\u7528 YouTube \u61c9\u7528\u7a0b\u5f0f");
            map.put("update_youtube_app_title", "\u66f4\u65b0 YouTube \u61c9\u7528\u7a0b\u5f0f");
            map.put("update_youtube_app_text", "\u60a8\u5fc5\u9808\u66f4\u65b0 YouTube \u61c9\u7528\u7a0b\u5f0f\uff0c\u9019\u500b\u61c9\u7528\u7a0b\u5f0f\u624d\u80fd\u904b\u4f5c\u3002");
            map.put("update_youtube_app_action", "\u66f4\u65b0 YouTube \u61c9\u7528\u7a0b\u5f0f");
        } else if ("zu".equals(str)) {
            map.put("error_initializing_player", "Kuvele iphutha ngenkathi kuqaliswa isidlali se-YouTube");
            map.put("get_youtube_app_title", "Thola uhlelo lokusebenza lwe-YouTube");
            map.put("get_youtube_app_text", "Lolu hlelo kusebenza angeke lusebenze ngaphandle kohlelo lokusebenza lwe-YouTube, olungekho kudivayisi yakho");
            map.put("get_youtube_app_action", "Thola uhelo lokusebenza lwe-YouTube");
            map.put("enable_youtube_app_title", "Nika amandla uhlelo lokusebenza lwe-YouTube");
            map.put("enable_youtube_app_text", "Lolu hlelo lokusebenza angeke lusebenze uma unganikanga amandla uhlelo lokusebenza lwe-YouTube.");
            map.put("enable_youtube_app_action", "Nika amandla uhlelo lokusebenza lwe-YouTube");
            map.put("update_youtube_app_title", "Buyekeza uhlelo lokusebenza lwe-YouTube");
            map.put("update_youtube_app_text", "Lolu hlelo lokusebenza angeke lusebenze uma ungabuyekezanga uhlelo lokusebenza lwe-YouTube.");
            map.put("update_youtube_app_action", "Buyekeza uhlelo lokusebenza lwe-YouTube");
        }
    }
}
