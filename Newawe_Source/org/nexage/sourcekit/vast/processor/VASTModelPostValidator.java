package org.nexage.sourcekit.vast.processor;

import android.text.TextUtils;
import java.util.List;
import mf.org.apache.xerces.impl.Constants;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.model.VASTMediaFile;
import org.nexage.sourcekit.vast.model.VASTModel;

public class VASTModelPostValidator {
    private static final String TAG = "VASTModelPostValidator";

    public static boolean validate(VASTModel model, VASTMediaPicker mediaPicker) {
        VASTLog.m3658d(TAG, Constants.DOM_VALIDATE);
        if (validateModel(model)) {
            boolean isValid = false;
            if (mediaPicker != null) {
                VASTMediaFile mediaFile = mediaPicker.pickVideo(model.getMediaFiles());
                if (mediaFile != null) {
                    String url = mediaFile.getValue();
                    if (!TextUtils.isEmpty(url)) {
                        isValid = true;
                        model.setPickedMediaFileURL(url);
                        VASTLog.m3658d(TAG, "mediaPicker selected mediaFile with URL " + url);
                    }
                }
            } else {
                VASTLog.m3663w(TAG, "mediaPicker: We don't have a compatible media file to play.");
            }
            VASTLog.m3658d(TAG, "Validator returns: " + (isValid ? "valid" : "not valid (no media file)"));
            return isValid;
        }
        VASTLog.m3658d(TAG, "Validator returns: not valid (invalid model)");
        return false;
    }

    private static boolean validateModel(VASTModel model) {
        VASTLog.m3658d(TAG, "validateModel");
        boolean isValid = true;
        List<String> impressions = model.getImpressions();
        if (impressions == null || impressions.size() == 0) {
            isValid = false;
        }
        List<VASTMediaFile> mediaFiles = model.getMediaFiles();
        if (mediaFiles != null && mediaFiles.size() != 0) {
            return isValid;
        }
        VASTLog.m3658d(TAG, "Validator error: mediaFile list invalid");
        return false;
    }
}
