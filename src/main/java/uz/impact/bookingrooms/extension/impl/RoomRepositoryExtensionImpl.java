package uz.impact.bookingrooms.extension.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import uz.impact.bookingrooms.entity.Room;
import uz.impact.bookingrooms.extension.RoomRepositoryExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class RoomRepositoryExtensionImpl implements RoomRepositoryExtension {
    private final EntityManager entityManager;
    @Override
    public List<Room> getAllRooms(Optional<String> search, Optional<String> type, Optional<Integer> page, Optional<Integer> pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
        Root<Room> root = query.from(Room.class);

        List<Predicate> predicates = new ArrayList<>();
        search.ifPresent(value->predicates.add(criteriaBuilder.equal(root.get("name"),value)));
        type.ifPresent(value->predicates.add(criteriaBuilder.equal(root.get("type").get("name"),value)));

        TypedQuery<Room> results = entityManager.createQuery(query.where(predicates.toArray(Predicate[]::new)));

        if(page.isPresent() && pageSize.isPresent()){
            if(page.get()<=0)return new ArrayList<>();
            results.setFirstResult((page.get()-1)*pageSize.get()).setMaxResults(pageSize.get());
        }
        return results.getResultList();
    }
}
