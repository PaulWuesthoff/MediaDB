package eventBasedCli;

import eventBasedCli.events.*;
import management.EventManager;
import management.HeadQuarter;
import mediaDB.Content;
import mediaDB.Tag;
import uploaderDB.Uploader;

import java.util.Collection;
import java.util.List;

public class EventHandler {
    private boolean executed;
    private List<?> contentList;
    private List<Uploader> uploaderList;
    private int amountOfUploads;
    private String stringContentList;
    private String addressAndDate;
    private IAddUploaderEvent addUploaderEvent;
    private IDeleteUploaderEvent deleteUploaderEvent;
    private IAddAudioContentEvent addAudioContentEvent;
    private IAddVideoContentEvent addVideoContentEvent;
    private IAddAudioVideoContentEvent addAudioVideoContentEvent;
    private IAddLicensedAudioContentEvent addLicensedAudioContentEvent;
    private IAddLicensedVideoContentEvent addLicensedVideoContentEvent;
    private IAddLicensedAudioVideoContentEvent addLicensedAudioVideoContentEvent;
    private IGetContentByNameEvent getContentByNameEvent;
    private IRequestAmountOfUploadsEvent requestAmountOfUploadsEvent;
    private IRequestPrintList requestPrintList;
    private IGetAddressAndDateEvent getAddressAndDateEvent;
    private IDeleteContentEvent deleteContentEvent;
    private IRequestUploaderList requestUploaderList;
    private IDeleteContentEventFX deleteContentEventFX;


    public void linkToEventManager(EventManager eventManager) {
        eventManager.registerAnswerRequestHandler(new IAnswerRequestEvent() {
            @Override
            public void onAnswerRequestEvent(AnswerRequestEventImpl answerRequestEvent) {
                EventHandler.this.onAnswerRequestEvent(answerRequestEvent);
            }
        });
        eventManager.registerGetContentByNameRequestHandler(new ISendContentTypes() {
            @Override
            public void onSendContentTypes(SendContentTypesImpl sendContentTypes) {
                getAnswerOfContentTypeRequest(sendContentTypes);
            }
        });
        eventManager.registerGetAmountOfContentRequestHandler(new IGetAmountOfUploadsAnswerEvent() {
            @Override
            public void onGetAmountOfUploadsAnswer(GetAmountOfUploadsAnswerEventImpl getAmountOfUploadsAnswerEvent) {
                getAmountOfUploadsAnswer(getAmountOfUploadsAnswerEvent);
            }
        });
        eventManager.registerPrintListAnswerRequestHandler(new IAnswerPrintListEvent() {
            @Override
            public void onAnswerPrintListEvent(AnswerPrintListEventImpl printListEvent) {
                getContentListFromEvent(printListEvent);
            }
        });
        eventManager.registerAnswerGetAddressAndDateEvent(new IAnswerAddressAndDateRequest() {
            @Override
            public void onAnswerAddressAndDateRequest(AnswerAddressAndDateRequestImpl answerAddressAndDateRequest) {
                getAddressAndDate(answerAddressAndDateRequest);
            }
        });
        eventManager.registerSendUploaderListEvent(new ISendUploaderListEvent() {
            @Override
            public void onSendUploaderListEvent(SendUploaderListEventImpl sendUploaderListEvent) {
                getUploaderList(sendUploaderListEvent);
            }
        });


    }


    private void onAnswerRequestEvent(AnswerRequestEventImpl answerRequestEvent) {
        this.executed = answerRequestEvent.isExecuted();
    }

    private void getAnswerOfContentTypeRequest(SendContentTypesImpl sendContentTypes) {
        this.contentList = sendContentTypes.getContentList();
    }

    public void registerPrintListEvent(IRequestPrintList requestPrintList) {
        this.requestPrintList = requestPrintList;
    }

    private void getContentListFromEvent(AnswerPrintListEventImpl answerPrintListEvent) {
        this.stringContentList = answerPrintListEvent.getContentList();
    }

    private void getAddressAndDate(AnswerAddressAndDateRequestImpl answerAddressAndDateRequest) {
        this.addressAndDate = answerAddressAndDateRequest.getAnswerString();
    }

    private void getUploaderList(SendUploaderListEventImpl sendUploaderListEvent) {
        this.uploaderList = sendUploaderListEvent.getUploaderList();
    }

    public void sendRequestPrintListEvent() {
        requestPrintList.onRequestPrintListEvent(new RequestPrintListImpl());
    }

    private void getAmountOfUploadsAnswer(GetAmountOfUploadsAnswerEventImpl getAmountOfUploadsAnswerEvent) {
        this.amountOfUploads = getAmountOfUploadsAnswerEvent.getAmount();
    }

    public void registerRequestAmountOfUploadsEvent(IRequestAmountOfUploadsEvent requestAmountOfUploadsEvent) {
        this.requestAmountOfUploadsEvent = requestAmountOfUploadsEvent;
    }

    public void sendRequestAmountOfUploadsEvent(String uploader) {
        requestAmountOfUploadsEvent.onRequestAmountOfUploadsEvent(new RequestAmountOfUploadsEventImpl(uploader));
    }

    public void registerGetAddressAndDateEvent(IGetAddressAndDateEvent getAddressAndDateEvent) {
        this.getAddressAndDateEvent = getAddressAndDateEvent;
    }

    public void registerRequestUploaderListEvent(IRequestUploaderList requestUploaderList) {
        this.requestUploaderList = requestUploaderList;
    }

    public void sendRequestUploaderListEvent() {
        requestUploaderList.onRequestUploaderListEvent(new RequestPrintListImpl());
    }

    public void sendGetAddressAndDateEvent(int position) {
        getAddressAndDateEvent.onGetAddressAndDateEvent(new GetAddressAndDateEventImpl(position));
    }

    public void registerAddUploaderEvent(IAddUploaderEvent addUploaderEvent) {
        this.addUploaderEvent = addUploaderEvent;
    }
    public void registerDeleteContentFXEvent(IDeleteContentEventFX deleteContentEventFX){
        this.deleteContentEventFX = deleteContentEventFX;
    }
    public void sendDeleteContentEvent(Content content){
        deleteContentEventFX.onDeleteContentEventFX(new DeleteContentEventFXImpl(content));
    }

    public void registerDeleteUploaderEvent(IDeleteUploaderEvent deleteUploaderEvent) {
        this.deleteUploaderEvent = deleteUploaderEvent;
    }

    public void sendDeleteUploaderEvent(String uploader) {
        deleteUploaderEvent.onDeleteUploaderEvent(new DeleteUploaderEventImpl(uploader));
    }

    public void registerAddAudioContentEvent(IAddAudioContentEvent addAudioContentEvent) {
        this.addAudioContentEvent = addAudioContentEvent;
    }

    public void registerGetContentByNameEvent(IGetContentByNameEvent getContentByNameEvent) {
        this.getContentByNameEvent = getContentByNameEvent;
    }

    public void registerDeleteContentEvent(IDeleteContentEvent deleteContentEvent) {
        this.deleteContentEvent = deleteContentEvent;
    }

    public void sendDeleteContentEvent(int position) {
        deleteContentEvent.onDeleteContentEvent(new DeleteContentEventImpl(position));
    }

    public void sendGetContentByNameEvent(String contentName) {
        getContentByNameEvent.onGetContentByNameEvent(new GetContentByNameEventImpl(contentName));
    }

    public void sendAddUploaderEvent(String uploader) {
        addUploaderEvent.onAddUploaderEvent(new AddUploaderEventImpl(uploader));
    }

    public void sendAddAudioContent(String uploader, String address, Collection<Tag> tags, int bitrate,
                                    long length, int samplingRate, String encoding) {
        addAudioContentEvent.onAddAudioContentEvent(new AddAudioContentEventImpl(uploader, address, tags, bitrate,
                length, samplingRate, encoding));
    }

    public void registerAddVideoContent(IAddVideoContentEvent addVideoContentEvent) {
        this.addVideoContentEvent = addVideoContentEvent;
    }

    public void sendAddVideoContent(String uploader, String address, Collection<Tag> tags, int bitrate,
                                    long length, int width, int height, String encoding) {
        addVideoContentEvent.onAddVideoContentEvent(new AddVideoContentEventImpl(uploader, address, tags, bitrate,
                length, width, height, encoding));
    }

    public void registerAddAudiVideoContent(IAddAudioVideoContentEvent addAudioVideoContentEvent) {
        this.addAudioVideoContentEvent = addAudioVideoContentEvent;
    }

    public void sendAudioVideoContentEvent(String uploader, String address, Collection<Tag> tags,
                                           int bitrate, long length, int samplingRate, String encoding, int width,
                                           int height) {
        addAudioVideoContentEvent.onAddAudioVideoContentEvent(new AddAudioVideoContentEventImpl(uploader, address, tags,
                bitrate, length, samplingRate, encoding, width, height));
    }

    public void registerAddLicensedAudioContent(IAddLicensedAudioContentEvent addLicensedAudioContentEvent) {
        this.addLicensedAudioContentEvent = addLicensedAudioContentEvent;
    }

    public void sendLicensedAudioContent(String uploader, String address, Collection<Tag> tags,
                                         int bitrate, long length, int samplingRate, String encoding, String holder) {
        addLicensedAudioContentEvent.onAddLicensedAudioContentEvent(new AddLicensedAudioContentEventImpl(uploader,
                address, tags, bitrate, length, samplingRate, encoding, holder));
    }

    public void registerAddLicensedVideoContent(IAddLicensedVideoContentEvent addLicensedVideoContentEvent) {
        this.addLicensedVideoContentEvent = addLicensedVideoContentEvent;
    }

    public void sendLicensedVideoContent(String uploader, String address, Collection<Tag> tags, int bitrate,
                                         long length, int width, int height, String encoding, String holder) {
        addLicensedVideoContentEvent.onAddLicensedVideoContentEvent(new AddLicensedVideoContentEventImpl(uploader,
                address, tags, bitrate, length, width, height, encoding, holder));
    }

    public void registerAddLicensedAudioVideoContent(IAddLicensedAudioVideoContentEvent addLicensedAudioVideoContentEvent) {
        this.addLicensedAudioVideoContentEvent = addLicensedAudioVideoContentEvent;
    }

    public void sendLicensedAudioVideoContentEvent(String uploader, String address, Collection<Tag> tags, int bitrate,
                                                   long length, int samplingRate, String encoding, int width,
                                                   int height, String holder) {
        addLicensedAudioVideoContentEvent.onAddLicensedAudioVideoContent(new AddLicensedAudioVideoContentEventImpl
                (uploader, address, tags, bitrate, length, samplingRate, encoding, width, height, holder));
    }

    public boolean isExecuted() {
        return executed;
    }

    public List<?> getContentList() {
        return contentList;
    }

    public int getAmountOfUploads() {
        return amountOfUploads;
    }

    public String getStringContentList() {
        return stringContentList;
    }

    public String getAddressAndDate() {
        return addressAndDate;
    }

    public List<Uploader> getUploaderList() {
        return uploaderList;
    }
}
