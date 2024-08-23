package io.sunshine0523.freeform.service;

import android.hardware.display.DisplayManagerInternal;
import android.util.Slog;
import android.view.InputEvent;
import android.view.Surface;

import io.sunshine0523.freeform.IMiFreeformDisplayCallback;

public class MiFreeformService {
    private static final String TAG = "Mi-Freeform/MiFreeformService";

    private DisplayManagerInternal displayManager = null;

    public MiFreeformService(DisplayManagerInternal displayManager) {
        this.displayManager = displayManager;
    }

    public void createFreeform(String name, IMiFreeformDisplayCallback callback,
                               int width, int height, int densityDpi, boolean secure,
                               boolean ownContentOnly, boolean shouldShowSystemDecorations, Surface surface,
                               float refreshRate, long presentationDeadlineNanos) {
        displayManager.createFreeformLocked(name, callback,
                width, height, densityDpi, secure,
                ownContentOnly, shouldShowSystemDecorations, surface,
                refreshRate, presentationDeadlineNanos);
        Slog.d(TAG, "createFreeform");
    }

    public void injectInputEvent(InputEvent event, int displayId) {
        try {
            event.setDisplayId(displayId);
            SystemServiceHolder.inputManagerService.injectInputEvent(event, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRunning() {
        return null != SystemServiceHolder.inputManagerService;
    }
}
