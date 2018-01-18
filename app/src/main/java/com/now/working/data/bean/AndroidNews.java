package com.now.working.data.bean;

import java.util.List;

/**
 * Created by Cyj on 17/12/28.
 */

public class AndroidNews {
    private boolean error;
    private List<Result> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    class Result{
        private String _id;
        private String desc;
        private String url;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "_id='" + _id + '\'' +
                    ", desc='" + desc + '\'' +
                    ", url='" + url + '\'' +
                    ", who='" + who + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AndroidNews{" +
                "error=" + error +
                ", results=" + results.toString() +
                '}';
    }
}
