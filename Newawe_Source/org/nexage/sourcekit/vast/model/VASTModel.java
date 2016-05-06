package org.nexage.sourcekit.vast.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import mf.org.apache.xml.serialize.LineSeparator;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.util.XmlTools;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VASTModel implements Serializable {
    private static String TAG = null;
    private static final String combinedTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking|/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private static final String durationXPATH = "//Duration";
    private static final String errorUrlXPATH = "//Error";
    private static final String impressionXPATH = "//Impression";
    private static final String inlineLinearTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/Linear/TrackingEvents/Tracking";
    private static final String inlineNonLinearTrackingXPATH = "/VASTS/VAST/Ad/InLine/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private static final String mediaFileXPATH = "//MediaFile";
    private static final long serialVersionUID = 4318368258447283733L;
    private static final String videoClicksXPATH = "//VideoClicks";
    private static final String wrapperLinearTrackingXPATH = "/VASTS/VAST/Ad/Wrapper/Creatives/Creative/Linear/TrackingEvents/Tracking";
    private static final String wrapperNonLinearTrackingXPATH = "/VASTS/VAST/Ad/Wrapper/Creatives/Creative/NonLinearAds/TrackingEvents/Tracking";
    private String pickedMediaFileURL;
    private transient Document vastsDocument;

    static {
        TAG = "VASTModel";
    }

    public VASTModel(Document vasts) {
        this.pickedMediaFileURL = null;
        this.vastsDocument = vasts;
    }

    public Document getVastsDocument() {
        return this.vastsDocument;
    }

    public HashMap<TRACKING_EVENTS_TYPE, List<String>> getTrackingUrls() {
        VASTLog.m3658d(TAG, "getTrackingUrls");
        HashMap<TRACKING_EVENTS_TYPE, List<String>> trackings = new HashMap();
        NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(combinedTrackingXPATH, this.vastsDocument, XPathConstants.NODESET);
        if (nodes == null) {
            return trackings;
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            String eventName = node.getAttributes().getNamedItem(NotificationCompatApi21.CATEGORY_EVENT).getNodeValue();
            try {
                TRACKING_EVENTS_TYPE key = TRACKING_EVENTS_TYPE.valueOf(eventName);
                try {
                    String trackingURL = XmlTools.getElementValue(node);
                    if (trackings.containsKey(key)) {
                        ((List) trackings.get(key)).add(trackingURL);
                    } else {
                        List<String> tracking = new ArrayList();
                        tracking.add(trackingURL);
                        trackings.put(key, tracking);
                    }
                } catch (Exception e) {
                    VASTLog.m3660e(TAG, e.getMessage(), e);
                    return null;
                }
            } catch (IllegalArgumentException e2) {
                VASTLog.m3663w(TAG, "Event:" + eventName + " is not valid. Skipping it.");
            }
        }
        return trackings;
    }

    public List<VASTMediaFile> getMediaFiles() {
        VASTLog.m3658d(TAG, "getMediaFiles");
        ArrayList<VASTMediaFile> mediaFiles = new ArrayList();
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(mediaFileXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodes == null) {
                return mediaFiles;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                BigInteger bigInteger;
                VASTMediaFile mediaFile = new VASTMediaFile();
                Node node = nodes.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attributeNode = attributes.getNamedItem("apiFramework");
                mediaFile.setApiFramework(attributeNode == null ? null : attributeNode.getNodeValue());
                attributeNode = attributes.getNamedItem("bitrate");
                if (attributeNode == null) {
                    bigInteger = null;
                } else {
                    bigInteger = new BigInteger(attributeNode.getNodeValue());
                }
                mediaFile.setBitrate(bigInteger);
                attributeNode = attributes.getNamedItem("delivery");
                mediaFile.setDelivery(attributeNode == null ? null : attributeNode.getNodeValue());
                attributeNode = attributes.getNamedItem("height");
                mediaFile.setHeight(attributeNode == null ? null : new BigInteger(attributeNode.getNodeValue()));
                attributeNode = attributes.getNamedItem("id");
                mediaFile.setId(attributeNode == null ? null : attributeNode.getNodeValue());
                attributeNode = attributes.getNamedItem("maintainAspectRatio");
                mediaFile.setMaintainAspectRatio(attributeNode == null ? null : Boolean.valueOf(attributeNode.getNodeValue()));
                attributeNode = attributes.getNamedItem("scalable");
                mediaFile.setScalable(attributeNode == null ? null : Boolean.valueOf(attributeNode.getNodeValue()));
                attributeNode = attributes.getNamedItem("type");
                mediaFile.setType(attributeNode == null ? null : attributeNode.getNodeValue());
                attributeNode = attributes.getNamedItem("width");
                mediaFile.setWidth(attributeNode == null ? null : new BigInteger(attributeNode.getNodeValue()));
                mediaFile.setValue(XmlTools.getElementValue(node));
                mediaFiles.add(mediaFile);
            }
            return mediaFiles;
        } catch (Exception e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public String getDuration() {
        VASTLog.m3658d(TAG, "getDuration");
        String duration = null;
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(durationXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodes != null) {
                for (int i = 0; i < nodes.getLength(); i++) {
                    duration = XmlTools.getElementValue(nodes.item(i));
                }
            }
            return duration;
        } catch (Exception e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public VideoClicks getVideoClicks() {
        VASTLog.m3658d(TAG, "getVideoClicks");
        VideoClicks videoClicks = new VideoClicks();
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(videoClicksXPATH, this.vastsDocument, XPathConstants.NODESET);
            if (nodes == null) {
                return videoClicks;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                NodeList childNodes = nodes.item(i).getChildNodes();
                for (int childIndex = 0; childIndex < childNodes.getLength(); childIndex++) {
                    Node child = childNodes.item(childIndex);
                    String nodeName = child.getNodeName();
                    if (nodeName.equalsIgnoreCase("ClickTracking")) {
                        videoClicks.getClickTracking().add(XmlTools.getElementValue(child));
                    } else if (nodeName.equalsIgnoreCase("ClickThrough")) {
                        videoClicks.setClickThrough(XmlTools.getElementValue(child));
                    } else if (nodeName.equalsIgnoreCase("CustomClick")) {
                        videoClicks.getCustomClick().add(XmlTools.getElementValue(child));
                    }
                }
            }
            return videoClicks;
        } catch (Exception e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public List<String> getImpressions() {
        VASTLog.m3658d(TAG, "getImpressions");
        return getListFromXPath(impressionXPATH);
    }

    public List<String> getErrorUrl() {
        VASTLog.m3658d(TAG, "getErrorUrl");
        return getListFromXPath(errorUrlXPATH);
    }

    private List<String> getListFromXPath(String xPath) {
        VASTLog.m3658d(TAG, "getListFromXPath");
        ArrayList<String> list = new ArrayList();
        try {
            NodeList nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(xPath, this.vastsDocument, XPathConstants.NODESET);
            if (nodes == null) {
                return list;
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                list.add(XmlTools.getElementValue(nodes.item(i)));
            }
            return list;
        } catch (Exception e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        VASTLog.m3658d(TAG, "writeObject: about to write");
        oos.defaultWriteObject();
        oos.writeObject(XmlTools.xmlDocumentToString(this.vastsDocument));
        VASTLog.m3658d(TAG, "done writing");
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        VASTLog.m3658d(TAG, "readObject: about to read");
        ois.defaultReadObject();
        String vastString = (String) ois.readObject();
        VASTLog.m3658d(TAG, "vastString data is:\n" + vastString + LineSeparator.Web);
        this.vastsDocument = XmlTools.stringToDocument(vastString);
        VASTLog.m3658d(TAG, "done reading");
    }

    public String getPickedMediaFileURL() {
        return this.pickedMediaFileURL;
    }

    public void setPickedMediaFileURL(String pickedMediaFileURL) {
        this.pickedMediaFileURL = pickedMediaFileURL;
    }
}
