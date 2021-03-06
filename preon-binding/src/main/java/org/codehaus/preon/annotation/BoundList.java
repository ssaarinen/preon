/**
 * Copyright (C) 2009-2010 Wilfred Springer
 *
 * This file is part of Preon.
 *
 * Preon is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2, or (at your option) any later version.
 *
 * Preon is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Preon; see the file COPYING. If not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is making a
 * combined work based on this library. Thus, the terms and conditions of the
 * GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules, and
 * to copy and distribute the resulting executable under terms of your choice,
 * provided that you also meet, for each linked independent module, the terms
 * and conditions of the license of that module. An independent module is a
 * module which is not derived from or based on this library. If you modify this
 * library, you may extend this exception to your version of the library, but
 * you are not obligated to do so. If you do not wish to do so, delete this
 * exception statement from your version.
 */
package org.codehaus.preon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.codehaus.preon.buffer.BitBuffer;

/**
 * The annotation used to mark {@link List} fields as potential candidates to be bound to a {@link BitBuffer}.
 *
 * @author Wilfred Springer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BoundList {

    /**
     * The type of object to be inserted into the {@link List}. Note that this allows you to have a field of a super
     * type of the actual type that you expect to inject. So you might have something like this:
     * <p/>
     * <pre>
     *         class A {
     *         }
     * <p/>
     *         class B extends A {
     *         }
     * <p/>
     *         ...
     *         &#064;BoundList(type=&quot;B&quot;)
     *         private List&lt;A&gt;; // List will contain instances of B.
     *         ...
     * </pre>
     *
     * @return The type of object to be inserted.
     */
    Class<?> type() default Void.class;

    /**
     * The types of objects to be decoded. Use this if you want the framework to select a certain class based on a
     * couple of leading bits prefixing the actual data. Note that it expects every type in the array to have the {@link
     * TypePrefix} annotation.
     *
     * @return The types of object to be decoded.
     */
    Class<?>[] types() default {};

    /**
     * The size of the List. Is expected to be interpreted as a Limbo expression.
     *
     * @return The size of the List, as a Limbo expression.
     */
    String size() default "";

    /**
     * The offset for each individual element. A Limbo expression, accepting a parameter 'index', representing the
     * current index. So an expression could be <code>index*2</code> or <code>offsets[index]</code>, assuming that a
     * certain offsets variable would be in scope.
     *
     * @return A Limbo expression, representing the offset for the individual elements.
     */
    String offset() default "";

    /** Indicates that the type prefix must be ignored. Note that this is fairly experimental. Use this with cause. */
    boolean ommitTypePrefix() default false;

    /**
     * The choices to select from, based on a prefix of a certain size.
     *
     * @return The choices to select from, based on a prefix of a certain size.
     */
    Choices selectFrom() default @Choices(alternatives = {});

}
