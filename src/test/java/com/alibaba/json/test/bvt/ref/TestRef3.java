package com.alibaba.json.test.bvt.ref;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.ParserConfig;

public class TestRef3 extends TestCase {

    public void test_0() throws Exception {
        Entity entity = new Entity(123, new Child());
        entity.getChild().setParent(entity);

        String text = JSON.toJSONString(entity);
        System.out.println(text);

        Entity entity2 = JSON.parseObject(text, Entity.class);

        Assert.assertEquals(entity2, entity2.getChild().getParent());

        System.out.println(JSON.toJSONString(entity2));
    }

    public static class Entity {

        private final int   id;
        private final Child child;

        @JSONCreator
        public Entity(@JSONField(name = "id") int id, @JSONField(name = "child") Child child){
            super();
            this.id = id;
            this.child = child;
        }

        public int getId() {
            return id;
        }

        public Child getChild() {
            return child;
        }

        public String toString() {
            return "Entity-" + id;
        }
    }

    public static class Child {

        private Entity parent;

        public Child(){

        }

        public Entity getParent() {
            return parent;
        }

        public void setParent(Entity parent) {
            this.parent = parent;
        }

        public String toString() {
            return "Child";
        }
    }
}