package com.startapp.android.publish;

import java.io.Serializable;

/* compiled from: StartAppSDK */
public class SDKAdPreferences implements Serializable {
    private static final long serialVersionUID = 1;
    private String age;
    private Gender gender;

    /* compiled from: StartAppSDK */
    public enum Gender {
        MALE("m"),
        FEMALE("f");
        
        private String gender;

        private Gender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return this.gender;
        }

        public String toString() {
            return getGender();
        }

        public static Gender parseString(String gender) {
            for (Gender gender2 : values()) {
                if (gender2.getGender().equals(gender)) {
                    return gender2;
                }
            }
            return null;
        }
    }

    public SDKAdPreferences() {
        this.gender = null;
        this.age = null;
    }

    public Gender getGender() {
        return this.gender;
    }

    public SDKAdPreferences setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getAge() {
        return this.age;
    }

    public SDKAdPreferences setAge(int age) {
        this.age = Integer.toString(age);
        return this;
    }

    public SDKAdPreferences setAge(String age) {
        this.age = age;
        return this;
    }

    public String toString() {
        return "SDKAdPreferences [gender=" + this.gender + ", age=" + this.age + "]";
    }
}
