package com.Newawe.ads.behavior.activityBehaviors;

import android.app.Activity;
import com.Newawe.ads.behavior.BehaviorAcceptor;
import com.Newawe.ads.behavior.BehaviorVisitor;

public abstract class ActivityVisitor implements BehaviorVisitor {
    abstract void visit(Activity activity);

    public void visit(BehaviorAcceptor acceptor) {
        if (acceptor instanceof Activity) {
            visit((Activity) acceptor);
        }
    }
}
