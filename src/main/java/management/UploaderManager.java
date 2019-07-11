package management;

import eventBasedCli.observers.IObservable;
import eventBasedCli.observers.IObserver;
import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UploaderManager implements Serializable {
    private List<Uploader> uploaderList;
    Collection<IObserver> observables;

    public UploaderManager() {
        uploaderList = new ArrayList<>();
        observables = new LinkedList<>();
    }

    public UploaderManager(List<Uploader> uploaderList) {
        this.uploaderList = uploaderList;
    }


    public boolean register(IObserver observer) {
        return observables.add(observer);
    }


    public void notifyObservers() {
        for (IObserver o : observables) {
            o.update();
        }
    }

    public boolean addUploader(Uploader uploader) {
        if (!checkName(uploader.getName())) {
            return false;
        }
        if (uploaderList.contains(searchForUploader(uploader.getName()))) {
            return false;
        }
        UploaderImpl newCustomer = new UploaderImpl(formatNameToUpperCase(uploader.getName()));
        boolean executed = uploaderList.add(newCustomer);
        notifyObservers();
        return executed;

    }

    public boolean deleteUploader(Uploader uploader) {
        if (!checkName(uploader.getName())) {
            return false;
        }
        if (searchForUploader(uploader.getName()) == null) {
            return false;
        }
        boolean executed = uploaderList.remove(searchForUploader(uploader.getName()));
        notifyObservers();
        return executed;
    }

    private boolean checkName(String name) {
        if (name == null) {
            return false;
        }
        if (name.isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean checkForRightCharacters = m.find();

        if (!checkForRightCharacters) {
            return false;
        }
        if (uploaderList.isEmpty()) {
            return true;
        }
        return true;
    }

    private String formatNameToUpperCase(String name) {
        String nameToUpperCase = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        return nameToUpperCase;
    }

    public List<Uploader> getUploaderList() {
        return uploaderList;
    }

    public Uploader getUploader(String name) {
        return searchForUploader(name);
    }

    private Uploader searchForUploader(String name) {
        String nameUpperCase = formatNameToUpperCase(name);

        for (Uploader uploader : uploaderList) {
            if (uploader.getName().equals(nameUpperCase)) {
                return uploader;
            }
        }
        return null;
    }


}

