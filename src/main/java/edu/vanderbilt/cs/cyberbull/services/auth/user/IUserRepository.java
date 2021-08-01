//package edu.vanderbilt.cs.cyberbull.services.auth.user;
//
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//public class IUserRepository implements UserRepository{
//    ArrayList<User> users;
//    public IUserRepository(){
//        users = new ArrayList<>();
//    }
//    @Override
//    public Optional<User> findByEmail(String email) {
//        return this.users.stream().filter(u->u.getEmail().equals(email)).findFirst();
//    }
//
//    @Override
//    public <S extends User> S save(S entity) {
//        users.add(entity);
//        return entity;
//    }
//
//    @Override
//    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
//        entities.forEach(e->users.add(e));
//        return entities;
//    }
//
//    @Override
//    public Optional<User> findById(Long aLong) {
//        return users.stream().filter(u->u.getId().compareTo(aLong) == 0).findFirst();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return users.stream().anyMatch(
//                u->u.getId().compareTo(aLong) == 0
//        );
//    }
//
//    @Override
//    public Iterable<User> findAll() {
//        return users;
//    }
//
//    @Override
//    public Iterable<User> findAllById(Iterable<Long> longs) {
//        ArrayList<Long> newLongs = new ArrayList<>();
//        longs.iterator().forEachRemaining(newLongs::add);
//        return users.stream().filter(u->newLongs.contains(u.getId())).collect(Collectors.toList());
//    }
//
//    @Override
//    public long count() {
//        return users.size();
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//        users.removeIf(u->u.getId().compareTo(aLong) == 0);
//    }
//
//    @Override
//    public void delete(User entity) {
//        users.remove(entity);
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//        ArrayList<Long> newLongs = new ArrayList<>();
//        longs.iterator().forEachRemaining(newLongs::add);
//        users.removeIf(u->newLongs.contains(u.getId()));
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends User> entities) {
//        entities.forEach(e->users.remove(e));
//    }
//
//    @Override
//    public void deleteAll() {
//        users.removeIf(u->true);
//    }
//}
