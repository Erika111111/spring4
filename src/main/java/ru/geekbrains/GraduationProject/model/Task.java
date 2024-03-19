package ru.geekbrains.GraduationProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.http.parser.Priority;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * класс задач
 */

public class Task {
        private UUID id;
        private String name;
        private String description;// описание задачи
        private Status status;
        private Priority priority;

//        public Task() {
//                this.id = UUID.randomUUID();
//        }
        public Task(String name, String description, UUID uuid) {
                this.id = UUID.randomUUID(); // указывает что id проставляется автоматически
                this.name = name;
                this.description = description;

        }

        public UUID getId() {

                return id;
        }

        public void setId(UUID id) {

                this.id = id;
        }

        public String getName() {

                return name;
        }

        public void setName(String name) {

                this.name = name;
        }

        public String getDescription() {

                return description;
        }

        public void setDescription(String description) {

                this.description = description;
        }

        public Status getStatus() {

                return status;
        }

        public void setStatus(Status status) {

                this.status = status;
        }


        public Priority getPriority() {
                return priority;
        }

        public void setPriority(Priority priority) {
                this.priority = priority;
        }

        /**
         * enum используется для перечисления чего либо. Мы прописываем конечное кол во вариантов ответа и потом вызывем его как поле
         */
        public enum Status{
                TO_DO,
                IN_PROGRESS,
                DONE
        }

        /**
         * варианты приоритета
         */
        public enum Priority{
                LOW,
                MEDIUM,
                HIGH
        }


}


