package jp.ne.doilux.gohoubigazou.domain;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * 完了ステータスを表すクラス
 */
@ToString(includeFields = false)
@AllArgsConstructor
public enum Status {
    DOING(0), DONE(1);

    private final Integer index;

    boolean isDoing() {
        return this.equals(DOING);
    }

    public Integer getIndex() {
        return index;
    }

    public static Status createBy(Integer index) {
        return Arrays.stream(Status.values()).filter(s -> Objects.equals(s.getIndex(), index)).
                findFirst().orElseThrow(
                () -> new IllegalStateException(String.format("Unsupported type %s.", index))
        );
    }

}
