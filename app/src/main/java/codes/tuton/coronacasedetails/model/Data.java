package codes.tuton.coronacasedetails.model;

public class Data {
    GlobalData Global;
    CountaryData[] Countries;

    public Data(CountaryData[] countries) {
        Countries = countries;
    }



    public Data(GlobalData global) {
        Global = global;
    }

    public GlobalData getGlobal() {
        return Global;
    }

    public void setGlobal(GlobalData global) {
        Global = global;
    }

    public CountaryData[] getCountries() {
        return Countries;
    }

    public void setCountries(CountaryData[] countries) {
        Countries = countries;
    }
}
