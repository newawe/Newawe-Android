package com.Newawe.suggestions;

import com.Newawe.MainNavigationActivity;
import com.Newawe.server.BaseServerClient;
import com.Newawe.server.BaseServerClient.OnRequestDoneListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.xml.sax.InputSource;

public class SuggestionsClient extends BaseServerClient {
    public static final int TAG = 99999;
    private SuggestionsListener listener;

    /* renamed from: com.Newawe.suggestions.SuggestionsClient.1 */
    class C09951 implements OnRequestDoneListener {
        final /* synthetic */ String val$search;

        C09951(String str) {
            this.val$search = str;
        }

        public void onRequestDone(String requestUrl, int tag, HttpResponse response) {
            if (tag == SuggestionsClient.TAG && response != null) {
                ArrayList<RemoteSuggestionItem> results;
                StringBuilder sb = new StringBuilder();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
                    while (true) {
                        String line = reader.readLine();
                        if (line != null) {
                            sb.append(line);
                        }
                        try {
                            break;
                        } catch (Exception e) {
                            results = new ArrayList();
                        }
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                results = SuggestionsClient.this.tryGetSuggestsFromXml(sb.toString());
                SuggestionsClient.this.listener.onReceiveSuggestions(results, this.val$search);
            }
        }
    }

    public SuggestionsClient(MainNavigationActivity activity) {
        super(activity);
    }

    public void setListener(SuggestionsListener listener) {
        this.listener = listener;
    }

    public void getSuggestionsAsync(String search) {
        if (search != null && search != StringUtils.EMPTY) {
            sendRequestAsync("http://google.com/complete/search?output=toolbar&q=" + URLEncoder.encode(search), TAG, new C09951(search));
        }
    }

    private ArrayList<RemoteSuggestionItem> tryGetSuggestsFromXml(String xml) {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SuggestionsHandler handler = new SuggestionsHandler();
            parser.parse(new InputSource(new StringReader(xml)), handler);
            return handler.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
