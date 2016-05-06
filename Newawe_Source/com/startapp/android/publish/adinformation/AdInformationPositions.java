package com.startapp.android.publish.adinformation;

import android.widget.RelativeLayout.LayoutParams;

/* compiled from: StartAppSDK */
public class AdInformationPositions {
    protected static final String f2637a;

    /* compiled from: StartAppSDK */
    public enum Position {
        TOP_LEFT(1, new int[]{10, 9}, -1),
        TOP_RIGHT(2, new int[]{10, 11}, 1),
        BOTTOM_LEFT(3, new int[]{12, 9}, -1),
        BOTTOM_RIGHT(4, new int[]{12, 11}, 1);
        
        private int animationMultiplier;
        private int index;
        private int[] rules;

        private Position(int index, int[] rules, int animationMultiplier) {
            this.rules = rules;
            this.animationMultiplier = animationMultiplier;
            this.index = index;
        }

        public void addRules(LayoutParams layoutPrms) {
            for (int addRule : this.rules) {
                layoutPrms.addRule(addRule);
            }
        }

        public int getIndex() {
            return this.index;
        }

        public int getAnimationStartMultiplier() {
            return this.animationMultiplier;
        }

        public static Position getByName(String name) {
            Position position = BOTTOM_LEFT;
            Position[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    position = values[i];
                }
            }
            return position;
        }

        public static Position getByIndex(long index) {
            Position position = BOTTOM_LEFT;
            Position[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (((long) values[i].getIndex()) == index) {
                    position = values[i];
                }
            }
            return position;
        }
    }

    static {
        f2637a = Position.BOTTOM_LEFT.name();
    }
}
