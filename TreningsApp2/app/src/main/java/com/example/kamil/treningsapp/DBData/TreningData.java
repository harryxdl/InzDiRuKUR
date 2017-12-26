package com.example.kamil.treningsapp.DBData;

import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Kamil on 15.10.2017.
 */

public class TreningData implements Parcelable {

    int iId;
    Date dateStart;
    Date dateEnd;
    int iDistacne;


    public TreningData(){

    }

    public TreningData(int iId, Date dateStart, Date dateEnd, int iDistacne) {
        this.iId = iId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.iDistacne = iDistacne;
    }
    public TreningData(Date dateStart, Date dateEnd, int iDistacne) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.iDistacne = iDistacne;
    }
    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getiDistacne() {
        return iDistacne;
    }

    public void setiDistacne(int iDistacne) {
        this.iDistacne = iDistacne;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.iId);
        dest.writeLong(this.dateStart != null ? this.dateStart.getTime() : -1);
        dest.writeLong(this.dateEnd != null ? this.dateEnd.getTime() : -1);
        dest.writeInt(this.iDistacne);
    }
    protected TreningData(Parcel in) {
        this.iId = in.readInt();
        long tmp_date_start = in.readLong();
        this.dateStart = tmp_date_start == -1 ? null : new Date(tmp_date_start);
        long tmp_date_end = in.readLong();
        this.dateEnd = tmp_date_end == -1 ? null : new Date(tmp_date_end);
        this.iDistacne = in.readInt();
    }

    public static final Parcelable.Creator<TreningData> CREATOR = new Parcelable.Creator<TreningData>() {
        @Override
        public TreningData createFromParcel(Parcel source) {
            return new TreningData(source);
        }

        @Override
        public TreningData[] newArray(int size) {
            return new TreningData[size];
        }
    };
}
