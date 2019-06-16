package management;

import eventBasedCli.EventHandler;
import eventBasedCli.events.*;
import mediaDB.Content;
import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;

import java.util.List;

public class EventManager {
    private HeadQuarter headQuarter;
    private IAnswerRequestEvent answerRequestEventHandler;
    private ISendContentTypes sendContentTypes;
    private IGetAmountOfUploadsAnswerEvent getAmountOfUploadsAnswerEvent;
    private IAnswerPrintListEvent answerPrintListEvent;
    private IAnswerAddressAndDateRequest answerAddressAndDateRequest;
    private ISendUploaderListEvent sendUploaderListEvent;

    public EventManager() {
        headQuarter = new HeadQuarter();
    }

    public void linkToEventHandler(EventHandler eventHandler) {
        eventHandler.registerAddUploaderEvent(new IAddUploaderEvent() {
            @Override
            public void onAddUploaderEvent(AddUploaderEventImpl addUploaderEvent) {
                if (AddUploaderEvent(addUploaderEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });

        eventHandler.registerAddAudioContentEvent(new IAddAudioContentEvent() {
            @Override
            public void onAddAudioContentEvent(AddAudioContentEventImpl addAudioContentEvent) {
                if (AddAudioContentEvent(addAudioContentEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
        eventHandler.registerAddVideoContent(new IAddVideoContentEvent() {
            @Override
            public void onAddVideoContentEvent(AddVideoContentEventImpl addVideoContentEvent) {
                if (AddVideoContentEvent(addVideoContentEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });

        eventHandler.registerAddAudiVideoContent(new IAddAudioVideoContentEvent() {
            @Override
            public void onAddAudioVideoContentEvent(AddAudioVideoContentEventImpl addAudioVideoContentEvent) {
                if (AddAudioVideoContent(addAudioVideoContentEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
        eventHandler.registerAddLicensedAudioContent(new IAddLicensedAudioContentEvent() {
            @Override
            public void onAddLicensedAudioContentEvent(AddLicensedAudioContentEventImpl addLicensedAudioContentEvent) {
                if (AddLicensedAudio(addLicensedAudioContentEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
        eventHandler.registerAddLicensedVideoContent(new IAddLicensedVideoContentEvent() {
            @Override
            public void onAddLicensedVideoContentEvent(AddLicensedVideoContentEventImpl addLicensedVideoContentEvent) {
                if (AddLicensedVideo(addLicensedVideoContentEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
        eventHandler.registerAddLicensedAudioVideoContent(new IAddLicensedAudioVideoContentEvent() {
            @Override
            public void onAddLicensedAudioVideoContent(AddLicensedAudioVideoContentEventImpl addLicensedAudioVideoContentEvent) {
                if (AddLicensedAudioVideoContent(addLicensedAudioVideoContentEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
        eventHandler.registerDeleteUploaderEvent(new IDeleteUploaderEvent() {
            @Override
            public void onDeleteUploaderEvent(DeleteUploaderEventImpl deleteUploaderEvent) {
                if (DeleteUploaderEvent(deleteUploaderEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
        eventHandler.registerGetContentByNameEvent(new IGetContentByNameEvent() {
            @Override
            public void onGetContentByNameEvent(GetContentByNameEventImpl getContentByNameEvent) {
                sendContentTypesEvent(getContentByNameEventAnswer(getContentByNameEvent));
            }
        });
        eventHandler.registerRequestAmountOfUploadsEvent(new IRequestAmountOfUploadsEvent() {
            @Override
            public void onRequestAmountOfUploadsEvent(RequestAmountOfUploadsEventImpl requestAmountOfUploadsEvent) {
                sendAmountOfUploadsForOneUploaderAnswer(getAmountOfUploads(requestAmountOfUploadsEvent));
            }
        });
        eventHandler.registerPrintListEvent(new IRequestPrintList() {
            @Override
            public void onRequestPrintListEvent(RequestPrintListImpl requestPrintList) {
                sendContentList(getContentList());
            }
        });
        eventHandler.registerGetAddressAndDateEvent(new IGetAddressAndDateEvent() {
            @Override
            public void onGetAddressAndDateEvent(GetAddressAndDateEventImpl getAddressAndDateEvent) {
                sendAddressAndDate(getAddressAndDate(getAddressAndDateEvent));
            }
        });
        eventHandler.registerDeleteContentEvent(new IDeleteContentEvent() {
            @Override
            public void onDeleteContentEvent(DeleteContentEventImpl deleteContentEvent) {
                if (deleteContent(deleteContentEvent)) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
        eventHandler.registerRequestUploaderListEvent(new IRequestUploaderList() {
            @Override
            public void onRequestUploaderListEvent(RequestPrintListImpl requestPrintList) {
                sendUploaderList(getUploaderList());
            }
        });
        eventHandler.registerDeleteContentFXEvent(new IDeleteContentEventFX() {
            @Override
            public void onDeleteContentEventFX(DeleteContentEventFXImpl deleteContentEventFX) {
                if (deleteContentFX(deleteContentEventFX.getContent())) {
                    sendAnswerRequestEvent(true);
                } else {
                    sendAnswerRequestEvent(false);
                }
            }
        });
    }

    private void sendUploaderList(List<Uploader> uploaderList) {
        sendUploaderListEvent.onSendUploaderListEvent(new SendUploaderListEventImpl(uploaderList));
    }

    public void registerSendUploaderListEvent(ISendUploaderListEvent sendUploaderListEvent) {
        this.sendUploaderListEvent = sendUploaderListEvent;
    }

    private List<Uploader> getUploaderList() {
        return headQuarter.getUploaderList();
    }

    private List<Content> getContentByNameEventAnswer(GetContentByNameEventImpl event) {
        Class cls = null;
        try {
            cls = Class.forName("mediaDB." + event.getContentName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return headQuarter.getContentByName(cls);
    }

    private int getAmountOfUploads(RequestAmountOfUploadsEventImpl event) {
        return headQuarter.getAmountOfUploadsForOneUploader(headQuarter.getUploader(event.getName()));
    }

    private String getAddressAndDate(GetAddressAndDateEventImpl event) {
        String addressAndDate = "";
        return addressAndDate += headQuarter.getAddress(headQuarter.getMediaList().get(event.getPosition() - 1)) + ", "
                + headQuarter.getTimestamp(headQuarter.getMediaList().get(event.getPosition() - 1));
    }

    public void registerAnswerGetAddressAndDateEvent(IAnswerAddressAndDateRequest answerAddressAndDateRequest) {
        this.answerAddressAndDateRequest = answerAddressAndDateRequest;
    }

    private void sendAddressAndDate(String addressAndDate) {
        answerAddressAndDateRequest.onAnswerAddressAndDateRequest(new AnswerAddressAndDateRequestImpl(addressAndDate));
    }

    public void registerAnswerRequestHandler(IAnswerRequestEvent answerRequestEventHandler) {
        this.answerRequestEventHandler = answerRequestEventHandler;
    }

    public void registerGetContentByNameRequestHandler(ISendContentTypes sendContentTypes) {
        this.sendContentTypes = sendContentTypes;
    }

    public void registerGetAmountOfContentRequestHandler(IGetAmountOfUploadsAnswerEvent getAmountOfUploadsAnswerEvent) {
        this.getAmountOfUploadsAnswerEvent = getAmountOfUploadsAnswerEvent;
    }

    private boolean deleteContentFX(Content content) {
        return headQuarter.deleteContent(content);
    }

    private void sendContentTypesEvent(List<?> contentList) {
        sendContentTypes.onSendContentTypes(new SendContentTypesImpl(contentList));
    }

    private void sendAmountOfUploadsForOneUploaderAnswer(int amount) {
        getAmountOfUploadsAnswerEvent.onGetAmountOfUploadsAnswer(new GetAmountOfUploadsAnswerEventImpl(amount));
    }

    private void sendAnswerRequestEvent(boolean executed) {
        answerRequestEventHandler.onAnswerRequestEvent(new AnswerRequestEventImpl(executed));
    }

    private void sendContentList(String contentList) {
        answerPrintListEvent.onAnswerPrintListEvent(new AnswerPrintListEventImpl(contentList));
    }

    private String getContentList() {
        return headQuarter.printList();
    }

    private boolean deleteContent(DeleteContentEventImpl deleteContentEvent) {
        return headQuarter.deleteContent(headQuarter.getMediaList().get(deleteContentEvent.getPosition() - 1));
    }

    public void registerPrintListAnswerRequestHandler(IAnswerPrintListEvent answerPrintListEvent) {
        this.answerPrintListEvent = answerPrintListEvent;
    }

    private boolean DeleteUploaderEvent(DeleteUploaderEventImpl event) {
        return headQuarter.deleteUploader(headQuarter.getUploader(event.getUploader()));
    }

    private boolean AddUploaderEvent(AddUploaderEventImpl event) {
        return headQuarter.addUploader(new UploaderImpl(event.getUploader()));
    }


    private boolean AddAudioContentEvent(AddAudioContentEventImpl event) {
        return headQuarter.addAudioContent(headQuarter.getUploader(event.getUploader()), event.getSamplingRate(),
                event.getEncoding(), event.getBitrate(), event.getLength(), event.getAddress(), event.getTags());
    }

    private boolean AddVideoContentEvent(AddVideoContentEventImpl event) {
        return headQuarter.addVideoContent(headQuarter.getUploader(event.getUploader()), event.getAddress(), event.getTags(),
                event.getBitrate(), event.getLength(), event.getWidth(), event.getHeight(), event.getEncoding());
    }

    private boolean AddAudioVideoContent(AddAudioVideoContentEventImpl event) {
        return headQuarter.addAudioVideoContent(headQuarter.getUploader(event.getUploader()), event.getAddress(), event.getTags(), event.getBitrate(),
                event.getLength(), event.getSamplingRate(), event.getEncoding(), event.getWidth(), event.getHeight());
    }

    private boolean AddLicensedAudio(AddLicensedAudioContentEventImpl event) {
        return headQuarter.addLicensedAudio(headQuarter.getUploader(event.getUploader()), event.getAddress(), event.getTags(), event.getBitrate(),
                event.getLength(), event.getSamplingRate(), event.getEncoding(), event.getHolder());
    }

    private boolean AddLicensedVideo(AddLicensedVideoContentEventImpl event) {
        return headQuarter.addLicensedVideo(headQuarter.getUploader(event.getUploader()), event.getAddress(), event.getTags(), event.getBitrate(),
                event.getLength(), event.getWidth(), event.getHeight(), event.getEncoding(), event.getHolder());
    }

    private boolean AddLicensedAudioVideoContent(AddLicensedAudioVideoContentEventImpl event) {
        return headQuarter.addLicensedAudioVideo(headQuarter.getUploader(event.getUploader()), event.getAddress(), event.getTags(), event.getBitrate(), event.getLength(),
                event.getSamplingRate(), event.getWidth(), event.getHeight(), event.getEncoding(), event.getHolder());
    }

    public HeadQuarter getHeadQuarter() {
        return headQuarter;
    }
}
