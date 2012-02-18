/*
 * Copyright (C) 2011-2012 Keyle
 *
 * This file is part of MyWolf
 *
 * MyWolf is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyWolf is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MyWolf. If not, see <http://www.gnu.org/licenses/>.
 */

package de.Keyle.MyWolf.Skill;

import de.Keyle.MyWolf.MyWolf;
import de.Keyle.MyWolf.util.MyWolfConfig;
import de.Keyle.MyWolf.util.MyWolfPermissions;
import de.Keyle.MyWolf.util.MyWolfUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyWolfSkill
{
    protected String Name;
    public static final Map<Integer, List<String>> SkillPerLevel = new HashMap<Integer, List<String>>();
    public static final Map<String, MyWolfSkill> RegisteredSkills = new HashMap<String, MyWolfSkill>();

    public MyWolfSkill(String Name)
    {
        this.Name = Name;
        registerSkill();
    }

    final void registerSkill()
    {
        if (!RegisteredSkills.containsKey(Name))
        {
            RegisteredSkills.put(this.Name, this);
        }
        else
        {
            MyWolfUtil.Log.info("[MyWolf] There is already a skill registered for " + Name);
        }
    }

    public final void registerSkill(String Name)
    {
        if (!RegisteredSkills.containsKey(Name))
        {
            RegisteredSkills.put(Name, this);
        }
        else
        {
            MyWolfUtil.Log.info("[MyWolf] There is already a skill registered for " + Name);
        }
    }

    public static boolean hasSkill(Map<String, Boolean> skills, String skill)
    {
        if(!MyWolfConfig.LevelSystem)
            return true;

        if (skills.containsKey(skill))
        {
            return skills.get(skill);
        }
        return false;
    }

    public String getName()
    {
        return this.Name;
    }

    public void run(MyWolf wolf, Object args)
    {
    }

    public void activate(MyWolf wolf, Object args)
    {
        if (!MyWolfPermissions.has(wolf.getOwner(), "MyWolf.Skills." + this.Name))
        {
            return;
        }
        wolf.Abilities.put(this.Name, true);
    }
}