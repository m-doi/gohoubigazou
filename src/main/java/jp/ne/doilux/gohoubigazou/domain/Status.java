package jp.ne.doilux.gohoubigazou.domain;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;

/**
 * 完了ステータスを表すクラス
 */
@ToString(includeFields = false)
@AllArgsConstructor
public enum Status {
    DOING, DONE;

    boolean isDoing() {
        return this.equals(DOING);
    }

}
