//package edu.vanderbilt.cs.cyberbull.services.auth.token;
//
//import lombok.AllArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@AllArgsConstructor
//public class IConfirmationTokenRepository implements ConfirmationTokenRepository{
//    ArrayList<ConfirmationToken> tokens;
//
//    public IConfirmationTokenRepository() {
//        tokens = new ArrayList<>();
//    }
//
//    @Override
//    public ConfirmationToken findByConfirmationToken(String confirmationToken) {
//        for (ConfirmationToken t : this.findAll()) {
//            if (t.getConfirmationToken().equals(confirmationToken)) {
//                return t;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public <S extends ConfirmationToken> S save(S entity) {
//        tokens.add(entity);
//        return entity;
//    }
//
//    @Override
//    public <S extends ConfirmationToken> Iterable<S> saveAll(Iterable<S> entities) {
//        entities.forEach(e->tokens.add(e));
//        return entities;
//    }
//
//    @Override
//    public Optional<ConfirmationToken> findById(Long aLong) {
//        return tokens.stream().filter(t->t.getId().compareTo(aLong) == 0).findFirst();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return tokens.stream().anyMatch(
//                t->t.getId().compareTo(aLong) == 0
//        );
//    }
//
//    @Override
//    public Iterable<ConfirmationToken> findAll() {
//        return tokens;
//    }
//
//    @Override
//    public Iterable<ConfirmationToken> findAllById(Iterable<Long> longs) {
//        ArrayList<Long> newLongs = new ArrayList<>();
//        longs.iterator().forEachRemaining(newLongs::add);
//        return tokens.stream().filter(t->newLongs.contains(t.getId())).collect(Collectors.toList());
//    }
//
//    @Override
//    public long count() {
//        return tokens.size();
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//        tokens.removeIf(t->t.getId().compareTo(aLong) == 0);
//    }
//
//    @Override
//    public void delete(ConfirmationToken entity) {
//        tokens.remove(entity);
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//        ArrayList<Long> newLongs = new ArrayList<>();
//        longs.iterator().forEachRemaining(newLongs::add);
//        tokens.removeIf(t->newLongs.contains(t.getId()));
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends ConfirmationToken> entities) {
//        entities.forEach(e->tokens.remove(e));
//    }
//
//    @Override
//    public void deleteAll() {
//        tokens.removeIf(t->true);
//    }
//}
