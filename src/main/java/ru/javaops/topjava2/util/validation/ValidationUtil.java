package ru.javaops.topjava2.util.validation;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava2.HasId;
import ru.javaops.topjava2.error.CreatingException;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.model.Role;
import ru.javaops.topjava2.web.AuthUser;

import java.time.LocalDateTime;
import java.util.Date;

import static ru.javaops.topjava2.util.DateTimeUtil.asLocalDateTime;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
    }

    public static <T> void checkPossibilityToCreate(T object, int id) {
        if (object != null) {
            throw new CreatingException("It is not possible to create the same record with id "
                    + id + " for current user");
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static boolean checkPrivileges(AuthUser authUser) {
        return authUser.getRoles().contains(Role.ADMIN);
    }
}