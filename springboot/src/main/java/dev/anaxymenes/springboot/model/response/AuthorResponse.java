package dev.anaxymenes.springboot.model.response;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public interface AuthorResponse {
    String getFullName();



    class AuthorBookCountResponse implements AuthorResponse{
        private final String fullName;
        private final int countOfBooks;

        public AuthorBookCountResponse(String firstName, String lastName, int countOfBooks) {
            this.fullName = firstName + StringUtils.SPACE + lastName;
            this.countOfBooks = countOfBooks;
        }

        public int getCountOfBooks() {
            return countOfBooks;
        }

        @Override
        public String getFullName() {
            return this.fullName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AuthorBookCountResponse)) return false;
            AuthorBookCountResponse that = (AuthorBookCountResponse) o;
            return countOfBooks == that.countOfBooks && Objects.equals(fullName, that.fullName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fullName, countOfBooks);
        }
    }



    class SimpleAuthorResponse implements AuthorResponse{
        private final String fullName;

        public SimpleAuthorResponse(String firstName, String lastName) {
            this.fullName = lastName + StringUtils.SPACE + firstName;
        }

        @Override
        public String getFullName() {
            return this.fullName;
        }
    }
}
