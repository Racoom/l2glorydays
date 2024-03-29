/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package quests;

import lineage2.commons.util.Rnd;
import lineage2.gameserver.model.instances.NpcInstance;
import lineage2.gameserver.model.quest.Quest;
import lineage2.gameserver.model.quest.QuestState;
import lineage2.gameserver.scripts.ScriptFile;

public class _906_TheCallofValakas extends Quest implements ScriptFile
{
	private static final int Klein = 31540;
	private static final int LavasaurusAlphaFragment = 21993;
	private static final int ValakasMinion = 29029;
	
	public _906_TheCallofValakas()
	{
		super(PARTY_ALL);
		addStartNpc(Klein);
		addKillId(ValakasMinion);
		addQuestItem(LavasaurusAlphaFragment);
	}
	
	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if (event.equalsIgnoreCase("klein_q906_04.htm"))
		{
			st.setState(STARTED);
			st.setCond(1);
			st.playSound(SOUND_ACCEPT);
		}
		else if (event.equalsIgnoreCase("klein_q906_07.htm"))
		{
			st.takeAllItems(LavasaurusAlphaFragment);
			st.giveItems(21895, 1);
			st.setState(COMPLETED);
			st.playSound(SOUND_FINISH);
			st.exitCurrentQuest(this);
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = "noquest";
		int cond = st.getCond();
		if (npc.getNpcId() == Klein)
		{
			switch (st.getState())
			{
				case CREATED:
					if (st.isNowAvailableByTime())
					{
						if (st.getPlayer().getLevel() >= 83)
						{
							if (st.getQuestItemsCount(7267) > 0)
							{
								htmltext = "klein_q906_01.htm";
							}
							else
							{
								htmltext = "klein_q906_00b.htm";
							}
						}
						else
						{
							htmltext = "klein_q906_00.htm";
							st.exitCurrentQuest(true);
						}
					}
					else
					{
						htmltext = "klein_q906_00a.htm";
					}
					break;
				case STARTED:
					if (cond == 1)
					{
						htmltext = "klein_q906_05.htm";
					}
					else if (cond == 2)
					{
						htmltext = "klein_q906_06.htm";
					}
					break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int cond = st.getCond();
		if (cond == 1)
		{
			if ((npc.getNpcId() == ValakasMinion) && Rnd.chance(40))
			{
				st.giveItems(LavasaurusAlphaFragment, 1);
				st.setCond(2);
			}
		}
		return null;
	}
	
	@Override
	public void onLoad()
	{
	}
	
	@Override
	public void onReload()
	{
	}
	
	@Override
	public void onShutdown()
	{
	}
}
