package org.nexage.sourcekit.vast.processor;

import java.util.List;
import org.nexage.sourcekit.vast.model.VASTMediaFile;

public interface VASTMediaPicker {
    VASTMediaFile pickVideo(List<VASTMediaFile> list);
}
