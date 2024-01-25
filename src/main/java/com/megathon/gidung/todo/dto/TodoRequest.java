package com.megathon.gidung.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequest {

    @Getter
    @Setter
    public static class Create {
        private String title;

        private Long memberId;
    }

    @Getter
    @Setter
    public static class Update {
        private String title;
    }

    @Getter
    @Setter
    public static class Delete {
        private Long id;
    }
}
